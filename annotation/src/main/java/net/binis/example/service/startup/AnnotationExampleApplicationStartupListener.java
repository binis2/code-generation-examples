package net.binis.example.service.startup;

import lombok.extern.slf4j.Slf4j;
import net.binis.codegen.spring.query.Queryable;
import net.binis.example.service.objects.Account;
import net.binis.example.service.objects.Transaction;
import net.binis.example.service.objects.User;
import net.binis.example.service.objects.base.Previewable;
import net.binis.example.service.objects.types.AccountType;
import net.binis.example.service.objects.types.TransactionType;
import net.binis.example.service.prototype.objects.types.TransactionTypePrototype;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import jakarta.persistence.Tuple;
import java.util.List;

import static java.util.Objects.nonNull;

@Slf4j
@Component
public class AnnotationExampleApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {

    public static final String DELIMITER = "------------------------------------------------------------------------";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Example queries:");

        showDownBasics();
        showDownRelations();
        showDownCollections();
    }

    private void showDownBasics() {
        log.info(DELIMITER);
        log.info("--- Basics");
        log.info(DELIMITER);

        log.info("Create or update entity:");
        var user = User.find().by().username("binis").ensure().with()
                .firstName("binis")
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

            Account.create()
                    .user(user)
                    .name("Closed account")
                    .type(AccountType.CHECKING)
                    .accountNumber("0003")
                    .pending(0.0)
                    .available(0.0)
                    .balance(0.0)
                    .active(false)
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
