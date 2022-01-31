/*Generated code by Binis' code generator.*/
package net.binis.example.core.objects;

import net.binis.example.core.objects.types.TransactionType;
import net.binis.example.core.objects.base.*;
import net.binis.example.core.base.BaseInterface;
import net.binis.codegen.spring.query.*;
import net.binis.codegen.creator.EntityCreatorModifier;
import net.binis.codegen.creator.EntityCreator;
import net.binis.codegen.collection.EmbeddedCodeCollection;
import net.binis.codegen.annotation.Default;
import javax.annotation.processing.Generated;
import java.util.function.Function;
import java.util.Optional;
import java.util.List;
import java.time.OffsetDateTime;

@Generated(value = "TransactionEntityPrototype", comments = "TransactionEntity")
@Default("net.binis.example.db.entity.TransactionEntity")
public interface Transaction extends BaseInterface, Taggable, Externalable<String>, Titleable, Descriptionable {

    // region starters
    static Transaction.Modify create() {
        return (Transaction.Modify) EntityCreatorModifier.create(Transaction.class).with();
    }

    static QueryStarter<Transaction, Transaction.QuerySelect<Transaction>, QueryAggregateOperation<QueryOperationFields<Transaction.QueryAggregate<Number, Transaction.QuerySelect<Number>>>>, QueryFieldsStart<Transaction, Transaction.QuerySelect<Transaction>>> find() {
        return (QueryStarter) EntityCreator.create(Transaction.QuerySelect.class);
    }
    // endregion

    Account getAccount();
    double getAmount();
    Account getCounterparty();
    OffsetDateTime getTimestamp();
    TransactionType getType();

    Transaction.Modify with();

    // region inner classes
    interface EmbeddedModify<T> extends Transaction.Fields<Transaction.EmbeddedModify<T>> {
        EmbeddedCodeCollection<EmbeddedModify<T>, Transaction, T> and();
    }

    interface Fields<T> extends BaseInterface.Fields<T> {
        T account(Account account);
        T amount(double amount);
        T counterparty(Account counterparty);
        T description(String description);
        T externalId(String externalId);
        T tag(Object tag);
        T timestamp(OffsetDateTime timestamp);
        T title(String title);
        T type(TransactionType type);
    }

    interface Modify extends Transaction.Fields<Transaction.Modify> {
        Transaction delete();
        Transaction detach();
        Transaction done();
        Transaction merge();
        Transaction refresh();
        Transaction save();
        Transaction saveAndFlush();
        Transaction transaction(Function<Transaction.Modify, Transaction> function);
    }

    interface QueryAggregate<QR, QA> extends QueryExecute<QR>, QueryAggregator<QA, QueryAggregateOperation<QueryOperationFields<Transaction.QueryAggregate<Transaction, Transaction.QuerySelect<Number>>>>> {
    }

    interface QueryFields<QR> extends QueryScript<QR>, Transaction.Fields<QR> {
        QR created(OffsetDateTime created);
        QR createdBy(String createdBy);
        QR modifiedBy(String modifiedBy);
    }

    interface QueryFieldsStart<QR, QS> extends QueryExecute<QR>, QueryWhere<QS>, QueryOperationFields<QueryFieldsStart<QR, QS>> {
    }

    interface QueryFuncs<QR> {
        QueryFunctions<Double, QR> amount();
        QueryFunctions<OffsetDateTime, QR> created();
        QueryFunctions<String, QR> createdBy();
        QueryFunctions<String, QR> description();
        QueryFunctions<String, QR> externalId();
        QueryFunctions<Long, QR> id();
        QueryFunctions<OffsetDateTime, QR> modified();
        QueryFunctions<String, QR> modifiedBy();
        QueryFunctions<OffsetDateTime, QR> timestamp();
        QueryFunctions<String, QR> title();
        QueryFunctions<TransactionType, QR> type();
    }

    interface QueryName<QS, QO, QR, QF> extends Transaction.QueryFields<QuerySelectOperation<QS, QO, QR>>, Transaction.QueryFuncs<QuerySelectOperation<QS, QO, QR>>, QueryFetch<QuerySelectOperation<QS, QO, QR>, QF> {
        Account.QueryName<QS, QO, QR, Account> account();
        Account.QueryName<QS, QO, QR, Account> counterparty();
    }

    interface QueryOperationFields<QR> extends QueryScript<QR> {
        QR account();
        QR amount();
        QR counterparty();
        QR created();
        QR createdBy();
        QR description();
        QR externalId();
        QR id();
        QR modified();
        QR modifiedBy();
        QR timestamp();
        QR title();
        QR type();
    }

    interface QueryOrder<QR> extends QueryOperationFields<QueryOrderOperation<Transaction.QueryOrder<QR>, QR>>, QueryExecute<QR>, QueryScript<QueryOrderOperation<Transaction.QueryOrder<QR>, QR>> {
    }

    interface QuerySelect<QR> extends QueryExecute<QR>, QueryModifiers<Transaction.QueryName<Transaction.QuerySelect<QR>, Transaction.QueryOrder<QR>, QR, Transaction>>, Transaction.QueryFields<QuerySelectOperation<Transaction.QuerySelect<QR>, Transaction.QueryOrder<QR>, QR>>, Transaction.QueryFuncs<QuerySelectOperation<Transaction.QuerySelect<QR>, Transaction.QueryOrder<QR>, QR>>, QueryOrderStart<QueryOperationFields<QueryOrderOperation<Transaction.QueryOrder<QR>, QR>>>, QueryBracket<QuerySelect<QR>> {
        Account.QueryName<Transaction.QuerySelect<QR>, Transaction.QueryOrder<QR>, QR, Account> account();
        Account.QueryName<Transaction.QuerySelect<QR>, Transaction.QueryOrder<QR>, QR, Account> counterparty();
    }
    // endregion
}
