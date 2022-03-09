package net.binis.example.service.startup;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.binis.codegen.spring.query.QueryProcessor;
import net.binis.codegen.spring.query.Queryable;
import net.binis.example.core.objects.Account;
import net.binis.example.core.objects.Transaction;
import net.binis.example.core.objects.User;
import net.binis.example.core.objects.base.Previewable;
import net.binis.example.core.objects.types.AccountType;
import net.binis.example.core.objects.types.TransactionType;
import net.binis.example.core.tools.Time;
import net.binis.example.db.view.AggregatedAccountView;
import net.binis.example.db.view.DynamicAccountView;
import net.binis.example.db.view.StaticAccountView;
import net.binis.example.db.view.StaticAccountView2;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.persistence.Tuple;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.nonNull;

@Slf4j
@Component
public class ExampleApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {

    public static final String DELIMITER = "------------------------------------------------------------------------";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        QueryProcessor.logQuery();
        QueryProcessor.logParams();
        log.info("Example queries:");

        showDownBasics();
        showDownRelations();
        showDownCollections();
        showDownAggregations();
        showDownProjections();
        showDownReferences();
        showDownAsync();
    }

    private void showDownBasics() {
        log.info(DELIMITER);
        log.info("--- Basics");
        log.info(DELIMITER);

        log.info("Create or update entity:");
        var user = User.find().by().username("binis").ensure().with()
                .firstName("Binis")
                .lastName("Belev")
                .username("binis")
                .email("binis@binis.dev")
                .save();

        log.info("Update entity:");
        user.with().firstName("Binis").save();

        if (!Account.find().by().user(user).exists()) {
            log.info("Create entity:");
            var checking = Account.create()
                    .name("Checking account")
                    .user(user)
                    .type(AccountType.CHECKING)
                    .accountNumber("0001")
                    .pending(100.0)
                    .available(500.0)
                    .balance(600.0)
                    .save();

            Transaction.create()
                    .account(checking)
                    .externalId("tr0001")
                    .title("First transaction!")
                    .type(TransactionType.DEPOSIT)
                    .amount(500.0)
                    .save();

            Transaction.create()
                    .account(checking)
                    .externalId("tr0002")
                    .title("Second transaction!")
                    .type(TransactionType.DEPOSIT)
                    .amount(200.0)
                    .save();

            Transaction.create()
                    .account(checking)
                    .externalId("tr0003")
                    .title("Third transaction!")
                    .type(TransactionType.CHARGE)
                    .amount(-100.0)
                    .save();

            Transaction.create()
                    .amount(1000000.0)
                    .externalId("asdf011")
                    .title("Big one!")
                    .type(TransactionType.DEPOSIT)
                    .account(Account.create()
                            .name("Savings Account")
                            .user(user)
                            .type(AccountType.SAVINGS)
                            .available(1000000.0)
                            .balance(1000000.0)
                            .accountNumber("0002")
                            .save())
                    .save();

            var closed = Account.create()
                    .user(user)
                    .name("Closed account")
                    .type(AccountType.CHECKING)
                    .accountNumber("0003")
                    .pending(0.0)
                    .available(0.0)
                    .balance(0.0)
                    .active(false)
                    .save();

            Transaction.create()
                    .amount(1000000.0)
                    .externalId("asdf012")
                    .title("with counterparty")
                    .type(TransactionType.DEPOSIT)
                    .account(checking)
                    .counterparty(closed)
                    .save();

            User.create()
                    .username("john")
                    .firstName("John")
                    .lastName("Smith")
                    .email("j.smith@binis.dev")
                    .save();
        }

        log.info("Showcase of custom interface and implementation:");
        log.info("(Note: Since we are accessing lazy collections we need transaction)");
        printUser(User.find().by().id(user.getId()).get().orElseThrow());
    }

    private void showDownRelations() {
        log.info(DELIMITER);
        log.info("--- Query by relation");
        log.info(DELIMITER);

        log.info("Simple filtering by relation:");
        Transaction.find().by().account().type(AccountType.CHECKING).order().amount().desc()
                .list().forEach(this::printTransactionSimple);

        log.info("Not so simple filtering by relation:");
        Transaction.find().by().account().user().username().contains("ini").order().timestamp().desc()
                .list().forEach(this::printTransactionSimple);

        log.info("In:");
        Transaction.find().by().account().type().in(List.of(AccountType.CHECKING, AccountType.OTHER)).order().amount().desc()
                .list().forEach(this::printTransactionSimple);

        log.info("Between:");
        Transaction.find().by().amount().between(50., 250.).order().amount().desc()
                .list().forEach(this::printTransactionSimple);


    }

    private void showDownCollections() {
        log.info(DELIMITER);
        log.info("--- Some collection goodies");
        log.info(DELIMITER);

        log.info("Get all users that have accounts:");
        printQuery(User.find().by().accounts().isNotEmpty())
                .list().forEach(this::printUser);

        log.info("Get all users and their active accounts:");
        printQuery(User.find().by().accounts().joinFetch(account -> account.where().active(true)))
                .list().forEach(this::printUser);

        log.info("Get all users order by the sum of amounts on their active accounts:");
        printQuery(User.find().by().accounts().join(account ->
                account.sum().balance().where().order().balance().desc())).tuples().forEach(this::printTuple);

        //log.info("Collection in Collection"); //TODO


//        log.info("A way to open transaction without injecting Transaction template:");
//        User.find().transaction(t -> {
//        });

    }

    private void showDownProjections() {
        log.info(DELIMITER);
        log.info("--- Projections");
        log.info(DELIMITER);

        log.info("Spring dynamic projections:");
        Account.find().by().user().fetch().top(DynamicAccountView.class).ifPresent(view -> log.info("Customer name with dynamic projection: {}", view.getCustomerName()));

        log.info("Static projections (Note that attempting to get unselected property will throw an exception):");
        Account.find().by(StaticAccountView.class).top()
                .ifPresent(view -> log.info("Customer name with static projection that keeps the original entity interface: {} {}", view.getUser().getFirstName(), view.getUser().getLastName()));

        log.info("Static projections:");
        Account.find().by().projection(StaticAccountView2.class).top()
                .ifPresent(view -> log.info("Customer name with static projection: {} {}", view.getUserFirstName(), view.getUserLastName()));

        log.info("Projections with custom queries:");
        Account.find().query("select sum(u.balance) as sumBalance, u.user.firstName || ' ' || u.user.lastName as userName from net.binis.example.core.objects.Account u group by userName").tuples(AggregatedAccountView.class).forEach(view ->
                log.info("Customer name with custom query: {}", view.getUserName()));

    }

    private void showDownAggregations() {
        log.info(DELIMITER);
        log.info("--- Aggregations");
        log.info(DELIMITER);

        log.info("Simple count: {}", Account.find().aggregate().cnt().id().get().orElse(0));
        log.info("Simple sum: {}", Account.find().aggregate().sum().available().get().orElse(0.0));

        log.info("Max with where clause: {}", Account.find().aggregate().max().available().where().active(true).get().orElse(0.0));

        log.info("Multiple aggregations:");
        Account.find().aggregate()
                .distinct().active().and()
                .min().available().and()
                .max().available().and()
                .sum().available().and()
                .avg().available().where().active(true).tuples().forEach(this::printTuple);

        Account.find().aggregate()
                .distinct().active().tuples().forEach(this::printTuple);
    }

    private void showDownReferences() {
        log.info(DELIMITER);
        log.info("--- References");
        log.info(DELIMITER);

        log.info("Note: Check JPA documentation for references. Since all data for reference entity is lazy initialized we need a transaction!");
        log.info("Note: These types of operations are useful for when someone wants to set relation without pulling the all of the related entity data!");

        User.find().transaction(t ->
                log.info("Get first name thru reference: {}", t.by().upper().username("BINIS").reference().map(User::getFirstName).orElse("Not Found")));

    }

    @SneakyThrows
    private void showDownAsync() {
        log.info(DELIMITER);
        log.info("--- Async");
        log.info(DELIMITER);

        log.info("Simple async save:");
        var job1 = User.find().by().username("binis").ensure().with().modified(Time.now()).async().save();
        log.info("Note: In this case the query is executed in the main thread, just the save operation is done in another!");
        log.info("Note: You need to use AsyncEntityModifier as your base modifier class!");

        log.info("Async lambda:");
        var job2 = User.find().async(t -> {
            log.info("Note: In this case everything is executed in different thread. Async also envelops the lambda with transaction!");
            return t.by().username("binis").get();
        });

        log.info("Now we wait!");
        var time = System.currentTimeMillis();
        CompletableFuture.allOf(job1, job2).join();
        log.info("Done ({}) (Took {} ms.)!", job2.get(), System.currentTimeMillis() - time);
        log.warn("WARNING: You can change the default thread pool executor with something like this - CodeExecutor.registerDefaultExecutor(CodeExecutor.syncExecutor());");
        log.warn("WARNING: or change the thread pool executor per flow - CodeExecutor.registerExecutor(\"MySpecialFlow\", CodeExecutor.syncExecutor());");

        log.info("Lets repeat it with a little twist!");

        log.info("Simple async save:");
        job1 = User.find().by().username("binis").ensure().with().modified(Time.now()).async().flow("differentThreadExecutor").delay(5, TimeUnit.SECONDS).save();
        log.info("Note: In this case the query is executed in the main thread, just the save operation is done in another!");
        log.info("Note: You need to use AsyncEntityModifier as your base modifier class!");

        log.info("Async lambda:");
        job2 = User.find().async("differentThreadExecutor", 5, TimeUnit.SECONDS, t -> {
            log.info("Note: In this case everything is executed in different thread. Async also envelops the lambda with transaction!");
            return t.by().username("binis").get();
        });

        log.info("Wait again:");
        time = System.currentTimeMillis();
        CompletableFuture.allOf(job1, job2).join();
        log.info("Done ({}) (Took {} ms.)!", job2.get(), System.currentTimeMillis() - time);

    }

    private void printUser(User user) {
        User.find().transaction(t -> {
            var usr = user.with().merge();
            printUserSimple(usr);
            usr.getAccounts().forEach(account -> {
                log.info("\t\tAccount: {}", account.as(Previewable.class).getPreview());
                account.getTransactions().forEach(transaction ->
                        log.info("\t\t\tTransaction: {}", transaction.as(Previewable.class).getPreview()));
            });
        });
    }

    private void printUserSimple(User user) {
        log.info("\tUser: {}", user.as(Previewable.class).getPreview());
    }

    private void printAccountSimple(Account account) {
        log.info("\tAccount: {}", account.as(Previewable.class).getPreview());
    }

    private void printTransactionSimple(Transaction transaction) {
        Transaction.find().transaction(t ->
                log.info("\tTransaction: {}", transaction.with().merge().as(Previewable.class).getPreview()));
    }


    private void printTuple(Tuple t) {
        var i = 0;
        for (var e : t.getElements()) {
            log.info("[{}] -> {}", nonNull(e.getAlias()) ? e.getAlias() : i, t.get(e));
            i++;
        }
    }

    private <T> T printQuery(T query) {
        if (query instanceof Queryable) {
            log.warn("Generated Query: {}", ((Queryable) query).print());
        }
        return query;
    }

}
