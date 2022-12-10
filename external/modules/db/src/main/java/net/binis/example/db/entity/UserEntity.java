/*Generated code by Binis' code generator.*/
package net.binis.example.db.entity;

import net.binis.example.core.objects.base.Previewable;
import net.binis.example.core.objects.User;
import net.binis.example.core.objects.Account;
import net.binis.codegen.spring.query.executor.QueryOrderer;
import net.binis.codegen.spring.query.executor.QueryExecutor;
import net.binis.codegen.spring.query.base.BaseQueryNameImpl;
import net.binis.codegen.spring.query.*;
import net.binis.codegen.spring.modifier.impl.AsyncEntityModifierImpl;
import net.binis.codegen.modifier.Modifiable;
import net.binis.codegen.factory.CodeFactory;
import net.binis.codegen.creator.EntityCreator;
import net.binis.codegen.collection.EmbeddedCodeListImpl;
import net.binis.codegen.collection.EmbeddedCodeCollection;
import lombok.ToString.Include;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.Data;
import javax.persistence.*;
import javax.annotation.processing.Generated;
import java.util.function.Function;
import java.util.Optional;
import java.util.List;
import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Generated(value = "UserEntityPrototype", comments = "User")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = UserEntity.TABLE_NAME)
@Table(name = UserEntity.TABLE_NAME, indexes = { @Index(name = "idx_" + UserEntity.TABLE_NAME + "_username", columnList = "username") })
@SuppressWarnings(value = "unchecked")
public class UserEntity extends BaseEntity implements User, Previewable, Modifiable<User.Modify> {

    // region constants
    public static final String TABLE_NAME = "users";

    private static final long serialVersionUID = 8340909241398601047L;
    // endregion

    @OneToMany(targetEntity = AccountEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    protected List<Account> accounts;

    @Column(nullable = false, unique = true)
    protected String email;

    protected String firstName;

    protected String lastName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected String password;

    @ToString.Include
    @Column(unique = true)
    protected String username;

    // region constructor & initializer
    {
        CodeFactory.registerType(User.class, UserEntity::new, (p, v, r) -> ((UserEntity) v).new UserEntitySoloModifyImpl(p));
        CodeFactory.registerType(User.QueryName.class, UserQueryNameImpl::new, null);
        CodeFactory.registerType(User.QuerySelect.class, UserQueryExecutorImpl::new, null);
        CodeFactory.registerType(User.QueryOperationFields.class, UserQueryExecutorImpl::new, null);
        CodeFactory.registerId(User.class, "id", Long.class);
    }

    public UserEntity() {
        super();
    }
    // endregion

    // region getters
    @OneToMany(targetEntity = AccountEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    public List<Account> getAccounts() {
        return accounts;
    }

    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }

    @Transient
    public String getPreview() {
        return this.username + " (" + this.firstName + " " + this.lastName + ")";
    }

    @ToString.Include
    @Column(unique = true)
    public String getUsername() {
        return username;
    }
    // endregion

    // region setters
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User.Modify with() {
        return new UserEntityModifyImpl(this);
    }
    // endregion

    // region inner classes
    protected class UserEntityEmbeddedModifyImpl<T, R> extends AsyncEntityModifierImpl<T, R> implements User.EmbeddedModify<T, R> {

        protected UserEntityEmbeddedModifyImpl(R parent) {
            super(parent);
        }

        public T accounts(List<Account> accounts) {
            UserEntity.this.accounts = accounts;
            return (T) this;
        }

        public EmbeddedCodeCollection accounts() {
            if (UserEntity.this.accounts == null) {
                UserEntity.this.accounts = new java.util.ArrayList<>();
            }
            return new EmbeddedCodeListImpl<>(this, UserEntity.this.accounts, Account.class);
        }

        public T email(String email) {
            UserEntity.this.email = email;
            return (T) this;
        }

        public T firstName(String firstName) {
            UserEntity.this.firstName = firstName;
            return (T) this;
        }

        public T id(Long id) {
            UserEntity.this.id = id;
            return (T) this;
        }

        public T lastName(String lastName) {
            UserEntity.this.lastName = lastName;
            return (T) this;
        }

        public T modified(OffsetDateTime modified) {
            UserEntity.this.modified = modified;
            return (T) this;
        }

        public T password(String password) {
            UserEntity.this.password = password;
            return (T) this;
        }

        public T username(String username) {
            UserEntity.this.username = username;
            return (T) this;
        }
    }

    protected class UserEntityModifyImpl extends UserEntityEmbeddedModifyImpl<User.Modify, User> implements User.Modify {

        protected UserEntityModifyImpl(User parent) {
            super(parent);
        }
    }

    protected class UserEntitySoloModifyImpl extends UserEntityEmbeddedModifyImpl implements User.EmbeddedSoloModify {

        protected UserEntitySoloModifyImpl(Object parent) {
            super(parent);
        }
    }

    protected static class UserQueryExecutorImpl extends QueryExecutor implements User.QueryUpdate, User.QuerySelect, User.QueryFieldsStart {

        protected UserQueryExecutorImpl() {
            super(User.class, () -> new UserQueryNameImpl(), parent -> parent);
        }

        public QueryJoinCollectionFunctions accounts() {
            return (QueryJoinCollectionFunctions) joinStart("accounts", Account.QueryOrder.class);
        }

        public QueryAggregateOperation aggregate() {
            return (QueryAggregateOperation) aggregateStart(new UserQueryOrderImpl(this, UserQueryExecutorImpl.this::aggregateIdentifier));
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

        public QuerySelectOperation email(String email) {
            return identifier("email", email);
        }

        public QueryFunctions email() {
            return identifier("email");
        }

        public QuerySelectOperation firstName(String firstName) {
            return identifier("firstName", firstName);
        }

        public QueryFunctions firstName() {
            return identifier("firstName");
        }

        public QuerySelectOperation id(Long id) {
            return identifier("id", id);
        }

        public QueryFunctions id() {
            return identifier("id");
        }

        public QuerySelectOperation lastName(String lastName) {
            return identifier("lastName", lastName);
        }

        public QueryFunctions lastName() {
            return identifier("lastName");
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

        public User.QueryOrder order() {
            return (User.QueryOrder) orderStart(new UserQueryOrderImpl(this, UserQueryExecutorImpl.this::orderIdentifier));
        }

        public QuerySelectOperation password(String password) {
            return identifier("password", password);
        }

        public QueryFunctions password() {
            return identifier("password");
        }

        public QuerySelectOperation username(String username) {
            return identifier("username", username);
        }

        public QueryFunctions username() {
            return identifier("username");
        }

        protected class UserQueryOrderImpl extends QueryOrderer implements User.QueryOrder, User.QueryAggregate {

            protected UserQueryOrderImpl(UserQueryExecutorImpl executor, Function<String, Object> func) {
                super(executor, func);
            }

            public QueryOrderOperation created() {
                return (QueryOrderOperation) func.apply("created");
            }

            public QueryOrderOperation createdBy() {
                return (QueryOrderOperation) func.apply("createdBy");
            }

            public QueryOrderOperation email() {
                return (QueryOrderOperation) func.apply("email");
            }

            public QueryOrderOperation firstName() {
                return (QueryOrderOperation) func.apply("firstName");
            }

            public QueryOrderOperation id() {
                return (QueryOrderOperation) func.apply("id");
            }

            public QueryOrderOperation lastName() {
                return (QueryOrderOperation) func.apply("lastName");
            }

            public QueryOrderOperation modified() {
                return (QueryOrderOperation) func.apply("modified");
            }

            public QueryOrderOperation modifiedBy() {
                return (QueryOrderOperation) func.apply("modifiedBy");
            }

            public QueryOrderOperation password() {
                return (QueryOrderOperation) func.apply("password");
            }

            public QueryOrderOperation username() {
                return (QueryOrderOperation) func.apply("username");
            }
        }
    }

    protected static class UserQueryNameImpl extends BaseQueryNameImpl implements User.QueryName, QueryEmbed {

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

        public QueryFunctions email() {
            return executor.identifier("email");
        }

        public QuerySelectOperation email(String email) {
            return executor.identifier("email", email);
        }

        public QueryFunctions firstName() {
            return executor.identifier("firstName");
        }

        public QuerySelectOperation firstName(String firstName) {
            return executor.identifier("firstName", firstName);
        }

        public QueryFunctions id() {
            return executor.identifier("id");
        }

        public QuerySelectOperation id(Long id) {
            return executor.identifier("id", id);
        }

        public QueryFunctions lastName() {
            return executor.identifier("lastName");
        }

        public QuerySelectOperation lastName(String lastName) {
            return executor.identifier("lastName", lastName);
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

        public QueryFunctions password() {
            return executor.identifier("password");
        }

        public QuerySelectOperation password(String password) {
            return executor.identifier("password", password);
        }

        public QueryFunctions username() {
            return executor.identifier("username");
        }

        public QuerySelectOperation username(String username) {
            return executor.identifier("username", username);
        }
    }
    // endregion
}
