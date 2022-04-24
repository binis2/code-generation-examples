/*Generated code by Binis' code generator.*/
package net.binis.example.core.objects;

import net.binis.example.core.objects.types.AccountType;
import net.binis.example.core.objects.base.*;
import net.binis.example.core.base.BaseInterface;
import net.binis.codegen.spring.query.*;
import net.binis.codegen.creator.EntityCreatorModifier;
import net.binis.codegen.creator.EntityCreator;
import net.binis.codegen.collection.EmbeddedCodeCollection;
import net.binis.codegen.annotation.*;
import javax.annotation.processing.Generated;
import java.util.function.Function;
import java.util.Optional;
import java.util.List;
import java.time.OffsetDateTime;

@Generated(value = "AccountEntityPrototype", comments = "AccountEntity")
@Default("net.binis.example.db.entity.AccountEntity")
public interface Account extends BaseInterface, Typeable<AccountType>, Externalable<String>, Userable, Nameable, Descriptionable {

    // region starters
    static Account.Modify create() {
        return (Account.Modify) EntityCreatorModifier.create(Account.class).with();
    }

    static QueryStarter<Account, Account.QuerySelect<Account>, QueryAggregateOperation<QueryOperationFields<Account.QueryAggregate<Number, Account.QuerySelect<Number>>>>, QueryFieldsStart<Account, Account.QuerySelect<Account>>> find() {
        return (QueryStarter) EntityCreator.create(Account.QuerySelect.class);
    }
    // endregion

    String getAccountNumber();
    double getAvailable();
    double getBalance();
    double getPending();
    List<Transaction> getTransactions();

    boolean isActive();

    Account.Modify with();

    // region inner classes
    interface EmbeddedModify<T> extends Account.Fields<Account.EmbeddedModify<T>> {
        Account.Modify _if(boolean condition, java.util.function.Consumer<Account.Modify> consumer);
        EmbeddedCodeCollection<EmbeddedModify<T>, Account, T> and();
        EmbeddedModify<T> transactions(List<Transaction> transactions);
        EmbeddedCodeCollection<Transaction.EmbeddedModify<Transaction.Modify>, Transaction, Account.EmbeddedModify<T>> transactions();
    }

    interface Fields<T> extends BaseInterface.Fields<T> {
        T accountNumber(String accountNumber);
        T active(boolean active);
        T available(double available);
        T balance(double balance);
        T description(String description);
        T externalId(String externalId);
        T name(String name);
        T pending(double pending);
        T type(AccountType type);
        T user(User user);
    }

    interface Modify extends Account.Fields<Account.Modify> {
        Account.Modify _if(boolean condition, java.util.function.Consumer<Account.Modify> consumer);
        Account delete();
        Account detach();
        Account done();
        Account merge();
        Account refresh();
        Account save();
        Account saveAndFlush();
        Account transaction(Function<Account.Modify, Account> function);
        Modify transactions(List<Transaction> transactions);
        EmbeddedCodeCollection<Transaction.EmbeddedModify<Transaction.Modify>, Transaction, Modify> transactions();
    }

    interface QueryAggregate<QR, QA> extends QueryExecute<QR>, QueryAggregator<QA, QueryAggregateOperation<QueryOperationFields<Account.QueryAggregate<Account, Account.QuerySelect<Number>>>>> {
    }

    interface QueryFields<QR> extends QueryScript<QR>, Account.Fields<QR> {
        QR created(OffsetDateTime created);
        QR createdBy(String createdBy);
        QR modifiedBy(String modifiedBy);
    }

    interface QueryFieldsStart<QR, QS> extends QueryExecute<QR>, QueryWhere<QS>, QueryOperationFields<QueryFieldsStart<QR, QS>> {
    }

    interface QueryFuncs<QR> {
        QueryFunctions<String, QR> accountNumber();
        QueryFunctions<Boolean, QR> active();
        QueryFunctions<Double, QR> available();
        QueryFunctions<Double, QR> balance();
        QueryFunctions<OffsetDateTime, QR> created();
        QueryFunctions<String, QR> createdBy();
        QueryFunctions<String, QR> description();
        QueryFunctions<String, QR> externalId();
        QueryFunctions<Long, QR> id();
        QueryFunctions<OffsetDateTime, QR> modified();
        QueryFunctions<String, QR> modifiedBy();
        QueryFunctions<String, QR> name();
        QueryFunctions<Double, QR> pending();
        QueryFunctions<AccountType, QR> type();
    }

    interface QueryName<QS, QO, QR, QF> extends Account.QueryFields<QuerySelectOperation<QS, QO, QR>>, Account.QueryFuncs<QuerySelectOperation<QS, QO, QR>>, QueryFetch<QuerySelectOperation<QS, QO, QR>, QF> {
        User.QueryName<QS, QO, QR, User> user();
    }

    interface QueryOperationFields<QR> extends QueryScript<QR> {
        QR accountNumber();
        QR active();
        QR available();
        QR balance();
        QR created();
        QR createdBy();
        QR description();
        QR externalId();
        QR id();
        QR modified();
        QR modifiedBy();
        QR name();
        QR pending();
        QR type();
        QR user();
    }

    interface QueryOrder<QR> extends QueryOperationFields<QueryOrderOperation<Account.QueryOrder<QR>, QR>>, QueryExecute<QR>, QueryScript<QueryOrderOperation<Account.QueryOrder<QR>, QR>> {
    }

    interface QuerySelect<QR> extends QueryExecute<QR>, QueryModifiers<Account.QueryName<Account.QuerySelect<QR>, Account.QueryOrder<QR>, QR, Account>>, Account.QueryFields<QuerySelectOperation<Account.QuerySelect<QR>, Account.QueryOrder<QR>, QR>>, Account.QueryFuncs<QuerySelectOperation<Account.QuerySelect<QR>, Account.QueryOrder<QR>, QR>>, QueryOrderStart<QueryOperationFields<QueryOrderOperation<Account.QueryOrder<QR>, QR>>>, QueryBracket<QuerySelect<QR>> {
        QueryJoinCollectionFunctions<Transaction, QuerySelectOperation<Account.QuerySelect<QR>, QueryOperationFields<QueryOrderOperation<Account.QueryOrder<QR>, QR>>, QR>, QueryJoinAggregateOperation<Transaction.QueryOperationFields<Transaction.QueryAggregate<Number, Transaction.QuerySelect<Number>>>, Transaction.QuerySelect<Number>>> transactions();
        User.QueryName<Account.QuerySelect<QR>, Account.QueryOrder<QR>, QR, User> user();
    }
    // endregion
}
