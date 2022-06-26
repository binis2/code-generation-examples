/*Generated code by Binis' code generator.*/
package net.binis.example.db.entity;

import org.springframework.data.annotation.Transient;
import org.hibernate.annotations.ColumnDefault;
import net.binis.example.core.objects.types.AccountType;
import net.binis.example.core.objects.base.*;
import net.binis.example.core.objects.User;
import net.binis.example.core.objects.Transaction;
import net.binis.example.core.objects.Account;
import net.binis.codegen.spring.query.executor.QueryOrderer;
import net.binis.codegen.spring.query.executor.QueryExecutor;
import net.binis.codegen.spring.query.base.BaseQueryNameImpl;
import net.binis.codegen.spring.query.*;
import net.binis.codegen.spring.modifier.impl.BaseEntityModifierImpl;
import net.binis.codegen.modifier.Modifiable;
import net.binis.codegen.factory.CodeFactory;
import net.binis.codegen.creator.EntityCreator;
import net.binis.codegen.collection.EmbeddedCodeListImpl;
import net.binis.codegen.collection.EmbeddedCodeCollection;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.Data;
import javax.persistence.*;
import javax.annotation.processing.Generated;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.Optional;
import static java.util.Objects.nonNull;
import java.util.List;
import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Generated(value = "AccountEntityPrototype", comments = "Account")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = AccountEntity.TABLE_NAME)
@Table(name = AccountEntity.TABLE_NAME, indexes = { @Index(name = "idx_" + AccountEntity.TABLE_NAME + "_account_number", columnList = "accountNumber"), @Index(name = "idx_" + AccountEntity.TABLE_NAME + "_active", columnList = "active"), @Index(name = "idx_" + AccountEntity.TABLE_NAME + "_external", columnList = "externalId", unique = true) })
public class AccountEntity extends BaseEntity implements Account, Previewable, Modifiable<Account.Modify> {

    // region constants
    public static final String TABLE_NAME = "accounts";

    private static final long serialVersionUID = -640283484493905851L;
    // endregion

    protected String accountNumber;

    @ColumnDefault("true")
    protected boolean active = true;

    @Column(nullable = false)
    @ColumnDefault("0.0")
    protected double available;

    @Column(nullable = false)
    @ColumnDefault("0.0")
    protected double balance;

    protected String description;

    protected String externalId;

    protected String name;

    @Column(nullable = false)
    @ColumnDefault("0.0")
    protected double pending;

    @OneToMany(targetEntity = TransactionEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonManagedReference
    protected List<Transaction> transactions;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "account_type", nullable = false)
    protected AccountType type = AccountType.CHECKING;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    protected User user;

    // region constructor & initializer
    {
        CodeFactory.registerType(Account.class, AccountEntity::new, (p, v) -> p instanceof EmbeddedCodeCollection ? ((AccountEntity) v).new AccountEntityCollectionModifyImpl(p) : ((AccountEntity) v).new AccountEntitySoloModifyImpl(p));
        CodeFactory.registerType(Account.QueryName.class, AccountQueryNameImpl::new, null);
        CodeFactory.registerType(Account.QuerySelect.class, AccountSelectQueryExecutorImpl::new, null);
        CodeFactory.registerType(Account.QueryOperationFields.class, AccountFieldsQueryExecutorImpl::new, null);
        CodeFactory.registerType(Account.QueryOrder.class, () -> Account.find().aggregate(), null);
        CodeFactory.registerId(Account.class, "id", Long.class);
    }

    public AccountEntity() {
        super();
    }
    // endregion

    // region getters
    @Transient
    public String getPreview() {
        return getName() + " [" + this.accountNumber + "] (user: " + (nonNull(this.user) ? this.user.getUsername() : "no user") + ")";
    }

    public Account.Modify with() {
        return new AccountEntityModifyImpl(this);
    }
    // endregion

    // region inner classes
    protected class AccountEntityCollectionModifyImpl extends AccountEntityEmbeddedModifyImpl implements Account.EmbeddedCollectionModify {

        protected AccountEntityCollectionModifyImpl(Object parent) {
            super(parent);
        }

        public EmbeddedCodeCollection _and() {
            return (EmbeddedCodeCollection) parent;
        }
    }

    protected class AccountEntityEmbeddedModifyImpl<T, R> extends BaseEntityModifierImpl<T, R> implements Account.EmbeddedModify<T, R> {

        protected AccountEntityEmbeddedModifyImpl(R parent) {
            super(parent);
        }

        public T accountNumber(String accountNumber) {
            AccountEntity.this.accountNumber = accountNumber;
            return (T) this;
        }

        public T active(boolean active) {
            AccountEntity.this.active = active;
            return (T) this;
        }

        public T available(double available) {
            AccountEntity.this.available = available;
            return (T) this;
        }

        public T balance(double balance) {
            AccountEntity.this.balance = balance;
            return (T) this;
        }

        public T description(String description) {
            AccountEntity.this.description = description;
            return (T) this;
        }

        public T externalId(String externalId) {
            AccountEntity.this.externalId = externalId;
            return (T) this;
        }

        public T id(Long id) {
            AccountEntity.this.id = id;
            return (T) this;
        }

        public T modified(OffsetDateTime modified) {
            AccountEntity.this.modified = modified;
            return (T) this;
        }

        public T name(String name) {
            AccountEntity.this.name = name;
            return (T) this;
        }

        public T pending(double pending) {
            AccountEntity.this.pending = pending;
            return (T) this;
        }

        public T transactions(List<Transaction> transactions) {
            AccountEntity.this.transactions = transactions;
            return (T) this;
        }

        public EmbeddedCodeCollection transactions() {
            if (AccountEntity.this.transactions == null) {
                AccountEntity.this.transactions = new java.util.ArrayList<>();
            }
            return new EmbeddedCodeListImpl<>(this, AccountEntity.this.transactions, Transaction.class);
        }

        public T type(AccountType type) {
            AccountEntity.this.type = type;
            return (T) this;
        }

        public T user(User user) {
            AccountEntity.this.user = user;
            return (T) this;
        }

        public User.EmbeddedSoloModify<Account.EmbeddedModify<T, R>> user() {
            if (AccountEntity.this.user == null) {
                AccountEntity.this.user = CodeFactory.create(User.class);
            }
            return CodeFactory.modify(this, AccountEntity.this.user, User.class);
        }
    }

    protected class AccountEntityModifyImpl extends AccountEntityEmbeddedModifyImpl<Account.Modify, Account> implements Account.Modify {

        protected AccountEntityModifyImpl(Account parent) {
            super(parent);
        }

        public Account.Modify user$(Consumer<User.Modify> init) {
            if (AccountEntity.this.user == null) {
                AccountEntity.this.user = CodeFactory.create(User.class);
            }
            init.accept(AccountEntity.this.user.with());
            return this;
        }
    }

    protected class AccountEntitySoloModifyImpl extends AccountEntityEmbeddedModifyImpl implements Account.EmbeddedSoloModify {

        protected AccountEntitySoloModifyImpl(Object parent) {
            super(parent);
        }
    }

    protected static class AccountFieldsQueryExecutorImpl extends AccountQueryExecutorImpl implements Account.QueryFieldsStart, EmbeddedFields {

        public User.QueryOperationFields user() {
            var result = EntityCreator.create(User.QueryOperationFields.class, "net.binis.example.db.entity.UserEntity");
            ((QueryEmbed) result).setParent("user", this);
            return result;
        }
    }

    protected static abstract class AccountQueryExecutorImpl extends QueryExecutor implements Account.QueryUpdate {

        protected AccountQueryExecutorImpl() {
            super(Account.class, () -> new AccountQueryNameImpl(), parent -> {
                var result = new AccountFieldsQueryExecutorImpl();
                result.parent = (QueryExecutor) parent;
                return result;
            });
        }

        public QuerySelectOperation accountNumber(String accountNumber) {
            return identifier("accountNumber", accountNumber);
        }

        public QueryFunctions accountNumber() {
            return identifier("accountNumber");
        }

        public QuerySelectOperation active(boolean active) {
            return identifier("active", active);
        }

        public QueryFunctions active() {
            return identifier("active");
        }

        public QueryAggregateOperation aggregate() {
            return (QueryAggregateOperation) aggregateStart(new AccountQueryOrderImpl(this, AccountQueryExecutorImpl.this::aggregateIdentifier));
        }

        public QuerySelectOperation available(double available) {
            return identifier("available", available);
        }

        public QueryFunctions available() {
            return identifier("available");
        }

        public QuerySelectOperation balance(double balance) {
            return identifier("balance", balance);
        }

        public QueryFunctions balance() {
            return identifier("balance");
        }

        public QuerySelectOperation created(OffsetDateTime created) {
            return identifier("created", created);
        }

        public QueryFunctions created() {
            return identifier("created");
        }

        public QuerySelectOperation createdBy(String createdBy) {
            return identifier("createdBy", createdBy);
        }

        public QueryFunctions createdBy() {
            return identifier("createdBy");
        }

        public QuerySelectOperation description(String description) {
            return identifier("description", description);
        }

        public QueryFunctions description() {
            return identifier("description");
        }

        public QuerySelectOperation externalId(String externalId) {
            return identifier("externalId", externalId);
        }

        public QueryFunctions externalId() {
            return identifier("externalId");
        }

        public QuerySelectOperation id(Long id) {
            return identifier("id", id);
        }

        public QueryFunctions id() {
            return identifier("id");
        }

        public QuerySelectOperation modified(OffsetDateTime modified) {
            return identifier("modified", modified);
        }

        public QueryFunctions modified() {
            return identifier("modified");
        }

        public QuerySelectOperation modifiedBy(String modifiedBy) {
            return identifier("modifiedBy", modifiedBy);
        }

        public QueryFunctions modifiedBy() {
            return identifier("modifiedBy");
        }

        public QuerySelectOperation name(String name) {
            return identifier("name", name);
        }

        public QueryFunctions name() {
            return identifier("name");
        }

        public Account.QueryOrder order() {
            return (Account.QueryOrder) orderStart(new AccountQueryOrderImpl(this, AccountQueryExecutorImpl.this::orderIdentifier));
        }

        public QuerySelectOperation pending(double pending) {
            return identifier("pending", pending);
        }

        public QueryFunctions pending() {
            return identifier("pending");
        }

        public QueryJoinCollectionFunctions transactions() {
            return (QueryJoinCollectionFunctions) joinStart("transactions", Transaction.QueryOrder.class);
        }

        public QuerySelectOperation type(AccountType type) {
            return identifier("type", type);
        }

        public QueryFunctions type() {
            return identifier("type");
        }

        public QuerySelectOperation user(User user) {
            return identifier("user", user);
        }

        protected class AccountQueryOrderImpl extends QueryOrderer implements Account.QueryOrder, Account.QueryAggregate {

            protected AccountQueryOrderImpl(AccountQueryExecutorImpl executor, Function<String, Object> func) {
                super(executor, func);
            }

            public QueryOrderOperation accountNumber() {
                return (QueryOrderOperation) func.apply("accountNumber");
            }

            public QueryOrderOperation active() {
                return (QueryOrderOperation) func.apply("active");
            }

            public QueryOrderOperation available() {
                return (QueryOrderOperation) func.apply("available");
            }

            public QueryOrderOperation balance() {
                return (QueryOrderOperation) func.apply("balance");
            }

            public QueryOrderOperation created() {
                return (QueryOrderOperation) func.apply("created");
            }

            public QueryOrderOperation createdBy() {
                return (QueryOrderOperation) func.apply("createdBy");
            }

            public QueryOrderOperation description() {
                return (QueryOrderOperation) func.apply("description");
            }

            public QueryOrderOperation externalId() {
                return (QueryOrderOperation) func.apply("externalId");
            }

            public QueryOrderOperation id() {
                return (QueryOrderOperation) func.apply("id");
            }

            public QueryOrderOperation modified() {
                return (QueryOrderOperation) func.apply("modified");
            }

            public QueryOrderOperation modifiedBy() {
                return (QueryOrderOperation) func.apply("modifiedBy");
            }

            public QueryOrderOperation name() {
                return (QueryOrderOperation) func.apply("name");
            }

            public QueryOrderOperation pending() {
                return (QueryOrderOperation) func.apply("pending");
            }

            public QueryOrderOperation type() {
                return (QueryOrderOperation) func.apply("type");
            }

            public User.QueryOperationFields user() {
                var result = EntityCreator.create(User.QueryOperationFields.class, "net.binis.example.db.entity.UserEntity");
                ((QueryEmbed) result).setParent("user", executor);
                return result;
            }
        }
    }

    protected static class AccountQueryNameImpl extends BaseQueryNameImpl implements Account.QueryName, QueryEmbed {

        public QueryFunctions accountNumber() {
            return executor.identifier("accountNumber");
        }

        public QuerySelectOperation accountNumber(String accountNumber) {
            return executor.identifier("accountNumber", accountNumber);
        }

        public QueryFunctions active() {
            return executor.identifier("active");
        }

        public QuerySelectOperation active(boolean active) {
            return executor.identifier("active", active);
        }

        public QueryFunctions available() {
            return executor.identifier("available");
        }

        public QuerySelectOperation available(double available) {
            return executor.identifier("available", available);
        }

        public QueryFunctions balance() {
            return executor.identifier("balance");
        }

        public QuerySelectOperation balance(double balance) {
            return executor.identifier("balance", balance);
        }

        public QueryFunctions created() {
            return executor.identifier("created");
        }

        public QuerySelectOperation created(OffsetDateTime created) {
            return executor.identifier("created", created);
        }

        public QueryFunctions createdBy() {
            return executor.identifier("createdBy");
        }

        public QuerySelectOperation createdBy(String createdBy) {
            return executor.identifier("createdBy", createdBy);
        }

        public QueryFunctions description() {
            return executor.identifier("description");
        }

        public QuerySelectOperation description(String description) {
            return executor.identifier("description", description);
        }

        public QueryFunctions externalId() {
            return executor.identifier("externalId");
        }

        public QuerySelectOperation externalId(String externalId) {
            return executor.identifier("externalId", externalId);
        }

        public QueryFunctions id() {
            return executor.identifier("id");
        }

        public QuerySelectOperation id(Long id) {
            return executor.identifier("id", id);
        }

        public QueryFunctions modified() {
            return executor.identifier("modified");
        }

        public QuerySelectOperation modified(OffsetDateTime modified) {
            return executor.identifier("modified", modified);
        }

        public QueryFunctions modifiedBy() {
            return executor.identifier("modifiedBy");
        }

        public QuerySelectOperation modifiedBy(String modifiedBy) {
            return executor.identifier("modifiedBy", modifiedBy);
        }

        public QueryFunctions name() {
            return executor.identifier("name");
        }

        public QuerySelectOperation name(String name) {
            return executor.identifier("name", name);
        }

        public QueryFunctions pending() {
            return executor.identifier("pending");
        }

        public QuerySelectOperation pending(double pending) {
            return executor.identifier("pending", pending);
        }

        public QueryFunctions type() {
            return executor.identifier("type");
        }

        public QuerySelectOperation type(AccountType type) {
            return executor.identifier("type", type);
        }

        public User.QueryName user() {
            var result = EntityCreator.create(User.QueryName.class, "net.binis.example.db.entity.UserEntity");
            ((QueryEmbed) result).setParent("user", executor);
            return result;
        }

        public QuerySelectOperation user(User user) {
            return executor.identifier("user", user);
        }
    }

    protected static class AccountSelectQueryExecutorImpl extends AccountQueryExecutorImpl implements Account.QuerySelect {

        public User.QueryName user() {
            var result = EntityCreator.create(User.QueryName.class, "net.binis.example.db.entity.UserEntity");
            ((QueryEmbed) result).setParent("user", this);
            return result;
        }
    }
    // endregion
}
