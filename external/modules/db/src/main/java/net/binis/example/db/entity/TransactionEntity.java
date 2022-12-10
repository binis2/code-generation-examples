/*Generated code by Binis' code generator.*/
package net.binis.example.db.entity;

import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ColumnDefault;
import net.binis.example.core.objects.types.TransactionType;
import net.binis.example.core.objects.base.*;
import net.binis.example.core.objects.Transaction;
import net.binis.example.core.objects.Account;
import net.binis.codegen.validation.validator.NullValidator;
import net.binis.codegen.validation.validator.LambdaValidator;
import net.binis.codegen.validation.flow.Validation;
import net.binis.codegen.spring.query.executor.QueryOrderer;
import net.binis.codegen.spring.query.executor.QueryExecutor;
import net.binis.codegen.spring.query.base.BaseQueryNameImpl;
import net.binis.codegen.spring.query.*;
import net.binis.codegen.spring.modifier.impl.BaseEntityModifierImpl;
import net.binis.codegen.modifier.Modifiable;
import net.binis.codegen.factory.CodeFactory;
import net.binis.codegen.creator.EntityCreator;
import net.binis.codegen.collection.EmbeddedCodeCollection;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.Data;
import javax.persistence.*;
import javax.annotation.processing.Generated;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.Optional;
import java.util.Objects;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Generated(value = "TransactionEntityPrototype", comments = "Transaction")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = TransactionEntity.TABLE_NAME)
@Table(name = TransactionEntity.TABLE_NAME, indexes = { @Index(name = "idx_" + TransactionEntity.TABLE_NAME + "_date", columnList = "timestamp"), @Index(name = "idx_" + TransactionEntity.TABLE_NAME + "_date_desc", columnList = "timestamp DESC"), @Index(name = "idx_" + TransactionEntity.TABLE_NAME + "_transaction_type", columnList = "transaction_type"), @Index(name = "idx_" + TransactionEntity.TABLE_NAME + "_external_id", columnList = "externalId") })
@FilterDef(name = "timestampAfter", parameters = { @ParamDef(name = "startDate", type = "java.time.OffsetDateTime") }, defaultCondition = "timestamp >= :startDate")
@SuppressWarnings(value = "unchecked")
public class TransactionEntity extends BaseEntity implements Transaction, Previewable, Modifiable<Transaction.Modify> {

    // region constants
    public static final String TABLE_NAME = "transactions";

    public static final long serialVersionUID = 2023385096577883838L;
    // endregion

    @OneToOne(targetEntity = AccountEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonBackReference
    protected Account account;

    @Column(nullable = false)
    protected double amount;

    @OneToOne(targetEntity = AccountEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "counterparty_id")
    @JsonBackReference
    protected Account counterparty;

    protected String description;

    protected String externalId;

    @Transient
    protected Object tag;

    @Column(nullable = false)
    @ColumnDefault("current_timestamp")
    protected OffsetDateTime timestamp = net.binis.example.core.tools.Time.now();

    protected String title;

    @Column(name = "transaction_type", nullable = false)
    protected TransactionType type;

    // region constructor & initializer
    {
        CodeFactory.registerType(Transaction.class, TransactionEntity::new, (p, v, r) -> ((TransactionEntity) v).new TransactionEntityCollectionModifyImpl(p));
        CodeFactory.registerType(Transaction.QueryName.class, TransactionQueryNameImpl::new, null);
        CodeFactory.registerType(Transaction.QuerySelect.class, TransactionSelectQueryExecutorImpl::new, null);
        CodeFactory.registerType(Transaction.QueryOperationFields.class, TransactionFieldsQueryExecutorImpl::new, null);
        CodeFactory.registerType(Transaction.QueryOrder.class, () -> Transaction.find().aggregate(), null);
        CodeFactory.registerId(Transaction.class, "id", Long.class);
    }

    public TransactionEntity() {
        super();
    }
    // endregion

    // region getters
    @Transient
    public String getPreview() {
        return this.timestamp.format(DateTimeFormatter.ISO_OFFSET_DATE) + " (" + this.title + " for $" + this.amount + ")" + " -> account: " + (Objects.nonNull(this.account) ? ((Previewable) this.account).getPreview() : "no account");
    }

    public Transaction.Modify with() {
        return new TransactionEntityModifyImpl(this);
    }
    // endregion

    // region inner classes
    protected class TransactionEntityCollectionModifyImpl extends TransactionEntityEmbeddedModifyImpl implements Transaction.EmbeddedCollectionModify {

        protected TransactionEntityCollectionModifyImpl(Object parent) {
            super(parent);
        }

        public EmbeddedCodeCollection _and() {
            return (EmbeddedCodeCollection) parent;
        }
    }

    @SuppressWarnings(value = "unchecked")
    protected class TransactionEntityEmbeddedModifyImpl<T, R> extends BaseEntityModifierImpl<T, R> implements Transaction.EmbeddedModify<T, R> {

        protected TransactionEntityEmbeddedModifyImpl(R parent) {
            super(parent);
        }

        public T account(Account account) {
            TransactionEntity.this.account = account;
            return (T) this;
        }

        public Account.EmbeddedSoloModify<Transaction.EmbeddedModify<T, R>> account() {
            if (TransactionEntity.this.account == null) {
                TransactionEntity.this.account = CodeFactory.create(Account.class);
            }
            return CodeFactory.modify(this, TransactionEntity.this.account, Account.class);
        }

        public T amount(double amount) {
            TransactionEntity.this.amount = amount;
            return (T) this;
        }

        public T counterparty(Account counterparty) {
            TransactionEntity.this.counterparty = counterparty;
            return (T) this;
        }

        public Account.EmbeddedSoloModify<Transaction.EmbeddedModify<T, R>> counterparty() {
            if (TransactionEntity.this.counterparty == null) {
                TransactionEntity.this.counterparty = CodeFactory.create(Account.class);
            }
            return CodeFactory.modify(this, TransactionEntity.this.counterparty, Account.class);
        }

        public T description(String description) {
            TransactionEntity.this.description = description;
            return (T) this;
        }

        public T externalId(String externalId) {
            TransactionEntity.this.externalId = externalId;
            return (T) this;
        }

        public T id(Long id) {
            TransactionEntity.this.id = id;
            return (T) this;
        }

        public T modified(OffsetDateTime modified) {
            TransactionEntity.this.modified = modified;
            return (T) this;
        }

        public T tag(Object tag) {
            TransactionEntity.this.tag = tag;
            return (T) this;
        }

        public T timestamp(OffsetDateTime timestamp) {
            Validation.start(this.getClass(), "timestamp", timestamp).validate(NullValidator.class, "'timestamp' can't be null!").perform(v -> TransactionEntity.this.timestamp = v);
            return (T) this;
        }

        public T title(String title) {
            Validation.start(this.getClass(), "title", title).validate(LambdaValidator.class, "(%s) Value can't be blank!", ((java.util.function.Predicate<String>) org.apache.commons.lang3.StringUtils::isNotBlank)).perform(v -> TransactionEntity.this.title = v);
            return (T) this;
        }

        public T type(TransactionType type) {
            Validation.start(this.getClass(), "type", type).validate(NullValidator.class, "'type' can't be null!").perform(v -> TransactionEntity.this.type = v);
            return (T) this;
        }
    }

    protected class TransactionEntityModifyImpl extends TransactionEntityEmbeddedModifyImpl<Transaction.Modify, Transaction> implements Transaction.Modify {

        protected TransactionEntityModifyImpl(Transaction parent) {
            super(parent);
        }

        public Transaction.Modify account$(Consumer<Account.Modify> init) {
            if (TransactionEntity.this.account == null) {
                TransactionEntity.this.account = CodeFactory.create(Account.class);
            }
            init.accept(TransactionEntity.this.account.with());
            return this;
        }

        public Transaction.Modify counterparty$(Consumer<Account.Modify> init) {
            if (TransactionEntity.this.counterparty == null) {
                TransactionEntity.this.counterparty = CodeFactory.create(Account.class);
            }
            init.accept(TransactionEntity.this.counterparty.with());
            return this;
        }
    }

    protected static class TransactionFieldsQueryExecutorImpl extends TransactionQueryExecutorImpl implements Transaction.QueryFieldsStart, EmbeddedFields {

        public Account.QueryOperationFields account() {
            var result = EntityCreator.create(Account.QueryOperationFields.class, "net.binis.example.db.entity.AccountEntity");
            ((QueryEmbed) result).setParent("account", this);
            return result;
        }

        public Account.QueryOperationFields counterparty() {
            var result = EntityCreator.create(Account.QueryOperationFields.class, "net.binis.example.db.entity.AccountEntity");
            ((QueryEmbed) result).setParent("counterparty", this);
            return result;
        }
    }

    protected static abstract class TransactionQueryExecutorImpl extends QueryExecutor implements Transaction.QueryUpdate {

        protected TransactionQueryExecutorImpl() {
            super(Transaction.class, () -> new TransactionQueryNameImpl(), parent -> {
                var result = new TransactionFieldsQueryExecutorImpl();
                result.parent = (QueryExecutor) parent;
                return result;
            });
        }

        public QuerySelectOperation account(Account account) {
            return identifier("account", account);
        }

        public QueryAggregateOperation aggregate() {
            return (QueryAggregateOperation) aggregateStart(new TransactionQueryOrderImpl(this, TransactionQueryExecutorImpl.this::aggregateIdentifier));
        }

        public QuerySelectOperation amount(double amount) {
            return identifier("amount", amount);
        }

        public QueryFunctions amount() {
            return identifier("amount");
        }

        public QuerySelectOperation counterparty(Account counterparty) {
            return identifier("counterparty", counterparty);
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

        public Transaction.QueryOrder order() {
            return (Transaction.QueryOrder) orderStart(new TransactionQueryOrderImpl(this, TransactionQueryExecutorImpl.this::orderIdentifier));
        }

        public QuerySelectOperation tag(Object tag) {
            return identifier("tag", tag);
        }

        public QuerySelectOperation timestamp(OffsetDateTime timestamp) {
            return identifier("timestamp", timestamp);
        }

        public QueryFunctions timestamp() {
            return identifier("timestamp");
        }

        public QuerySelectOperation title(String title) {
            return identifier("title", title);
        }

        public QueryFunctions title() {
            return identifier("title");
        }

        public QuerySelectOperation type(TransactionType type) {
            return identifier("type", type);
        }

        public QueryFunctions type() {
            return identifier("type");
        }

        protected class TransactionQueryOrderImpl extends QueryOrderer implements Transaction.QueryOrder, Transaction.QueryAggregate {

            protected TransactionQueryOrderImpl(TransactionQueryExecutorImpl executor, Function<String, Object> func) {
                super(executor, func);
            }

            public Account.QueryOperationFields account() {
                var result = EntityCreator.create(Account.QueryOperationFields.class, "net.binis.example.db.entity.AccountEntity");
                ((QueryEmbed) result).setParent("account", executor);
                return result;
            }

            public QueryOrderOperation amount() {
                return (QueryOrderOperation) func.apply("amount");
            }

            public Account.QueryOperationFields counterparty() {
                var result = EntityCreator.create(Account.QueryOperationFields.class, "net.binis.example.db.entity.AccountEntity");
                ((QueryEmbed) result).setParent("counterparty", executor);
                return result;
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

            public QueryOrderOperation timestamp() {
                return (QueryOrderOperation) func.apply("timestamp");
            }

            public QueryOrderOperation title() {
                return (QueryOrderOperation) func.apply("title");
            }

            public QueryOrderOperation type() {
                return (QueryOrderOperation) func.apply("type");
            }
        }
    }

    protected static class TransactionQueryNameImpl extends BaseQueryNameImpl implements Transaction.QueryName, QueryEmbed {

        public Account.QueryName account() {
            var result = EntityCreator.create(Account.QueryName.class, "net.binis.example.db.entity.AccountEntity");
            ((QueryEmbed) result).setParent("account", executor);
            return result;
        }

        public QuerySelectOperation account(Account account) {
            return executor.identifier("account", account);
        }

        public QueryFunctions amount() {
            return executor.identifier("amount");
        }

        public QuerySelectOperation amount(double amount) {
            return executor.identifier("amount", amount);
        }

        public Account.QueryName counterparty() {
            var result = EntityCreator.create(Account.QueryName.class, "net.binis.example.db.entity.AccountEntity");
            ((QueryEmbed) result).setParent("counterparty", executor);
            return result;
        }

        public QuerySelectOperation counterparty(Account counterparty) {
            return executor.identifier("counterparty", counterparty);
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

        public QuerySelectOperation tag(Object tag) {
            return executor.identifier("tag", tag);
        }

        public QueryFunctions timestamp() {
            return executor.identifier("timestamp");
        }

        public QuerySelectOperation timestamp(OffsetDateTime timestamp) {
            return executor.identifier("timestamp", timestamp);
        }

        public QueryFunctions title() {
            return executor.identifier("title");
        }

        public QuerySelectOperation title(String title) {
            return executor.identifier("title", title);
        }

        public QueryFunctions type() {
            return executor.identifier("type");
        }

        public QuerySelectOperation type(TransactionType type) {
            return executor.identifier("type", type);
        }
    }

    protected static class TransactionSelectQueryExecutorImpl extends TransactionQueryExecutorImpl implements Transaction.QuerySelect {

        public Account.QueryName account() {
            var result = EntityCreator.create(Account.QueryName.class, "net.binis.example.db.entity.AccountEntity");
            ((QueryEmbed) result).setParent("account", this);
            return result;
        }

        public Account.QueryName counterparty() {
            var result = EntityCreator.create(Account.QueryName.class, "net.binis.example.db.entity.AccountEntity");
            ((QueryEmbed) result).setParent("counterparty", this);
            return result;
        }
    }
    // endregion
}
