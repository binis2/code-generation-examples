/*Generated code by Binis' code generator.*/
package net.binis.example.core.objects;

import net.binis.example.core.base.BaseInterface;
import net.binis.codegen.spring.query.*;
import net.binis.codegen.spring.modifier.AsyncEntityModifier;
import net.binis.codegen.modifier.BaseModifier;
import net.binis.codegen.creator.EntityCreatorModifier;
import net.binis.codegen.creator.EntityCreator;
import net.binis.codegen.collection.EmbeddedCodeCollection;
import net.binis.codegen.annotation.Default;
import javax.annotation.processing.Generated;
import java.util.Optional;
import java.util.List;
import java.time.OffsetDateTime;

@Generated(value = "UserEntityPrototype", comments = "UserEntity")
@Default("net.binis.example.db.entity.UserEntity")
public interface User extends BaseInterface {

    // region starters
    static User.Modify create() {
        return (User.Modify) EntityCreatorModifier.create(User.class).with();
    }

    static QueryStarter<User, User.QuerySelect<User>, QueryAggregateOperation<QueryOperationFields<User.QueryAggregate<Number, User.QuerySelect<Number>>>>, QueryFieldsStart<User, User.QuerySelect<User>>, QueryUpdate<User, User.QuerySelect<User>>> find() {
        return (QueryStarter) EntityCreator.create(User.QuerySelect.class);
    }
    // endregion

    List<Account> getAccounts();
    String getEmail();
    String getFirstName();
    String getLastName();
    String getPassword();
    String getUsername();

    User.Modify with();

    // region inner classes
    interface EmbeddedModify<T, R> extends BaseModifier<T, R>, User.Fields<T> {
        T accounts(List<Account> accounts);
        EmbeddedCodeCollection<Account.EmbeddedCollectionModify<User.EmbeddedModify<T, R>>, Account, T> accounts();
    }

    interface EmbeddedSoloModify<R> extends User.EmbeddedModify<User.EmbeddedSoloModify<R>, R> {
    }

    interface Fields<T> extends BaseInterface.Fields<T> {
        T email(String email);
        T firstName(String firstName);
        T lastName(String lastName);
        T password(String password);
        T username(String username);
    }

    interface Modify extends EmbeddedModify<User.Modify, User>, AsyncEntityModifier<User.Modify, User> {
    }

    interface QueryAggregate<QR, QA> extends QueryExecute<QR>, QueryAggregator<QA, QueryAggregateOperation<QueryOperationFields<User.QueryAggregate<User, User.QuerySelect<Number>>>>> {
    }

    interface QueryFields<QR> extends QueryScript<QR>, User.Fields<QR> {
        QR created(OffsetDateTime created);
        QR createdBy(String createdBy);
        QR modifiedBy(String modifiedBy);
    }

    interface QueryFieldsStart<QR, QS> extends QueryExecute<QR>, QueryWhere<QS>, QueryOperationFields<QueryFieldsStart<QR, QS>> {
    }

    interface QueryFuncs<QR> {
        QueryFunctions<OffsetDateTime, QR> created();
        QueryFunctions<String, QR> createdBy();
        QueryFunctions<String, QR> email();
        QueryFunctions<String, QR> firstName();
        QueryFunctions<Long, QR> id();
        QueryFunctions<String, QR> lastName();
        QueryFunctions<OffsetDateTime, QR> modified();
        QueryFunctions<String, QR> modifiedBy();
        QueryFunctions<String, QR> password();
        QueryFunctions<String, QR> username();
    }

    interface QueryName<QS, QO, QR, QF> extends User.QueryFields<QuerySelectOperation<QS, QO, QR>>, User.QueryFuncs<QuerySelectOperation<QS, QO, QR>>, QueryFetch<QuerySelectOperation<QS, QO, QR>, QF> {
    }

    interface QueryOperationFields<QR> extends QueryScript<QR>, QuerySelf<QR> {
        QR created();
        QR createdBy();
        QR email();
        QR firstName();
        QR id();
        QR lastName();
        QR modified();
        QR modifiedBy();
        QR password();
        QR username();
    }

    interface QueryOrder<QR> extends QueryOperationFields<QueryOrderOperation<User.QueryOrder<QR>, QR>>, QueryExecute<QR> {
    }

    interface QuerySelect<QR> extends QueryExecute<QR>, QueryModifiers<User.QueryName<User.QuerySelect<QR>, User.QueryOrder<QR>, QR, User>>, User.QueryFields<QuerySelectOperation<User.QuerySelect<QR>, User.QueryOrder<QR>, QR>>, User.QueryFuncs<QuerySelectOperation<User.QuerySelect<QR>, User.QueryOrder<QR>, QR>>, QueryOrderStart<QueryOperationFields<QueryOrderOperation<User.QueryOrder<QR>, QR>>>, QueryBracket<QuerySelect<QR>> {
        QueryJoinCollectionFunctions<Account, QuerySelectOperation<User.QuerySelect<QR>, QueryOperationFields<QueryOrderOperation<User.QueryOrder<QR>, QR>>, QR>, QueryJoinAggregateOperation<Account.QueryOperationFields<Account.QueryAggregate<Number, Account.QuerySelect<Number>>>, Account.QuerySelect<Number>>> accounts();
    }

    interface QueryUpdate<QR, QS> extends QueryFields<QueryUpdate<QR, QS>>, QueryWhere<QS>, QueryScript<QueryUpdate<QR, QS>>, UpdatableQuery {
    }
    // endregion
}
