/*Generated code by Binis' code generator.*/
package net.binis.example.db.entity;

import net.binis.example.core.objects.base.Previewable;
import net.binis.example.core.objects.User;
import net.binis.example.core.objects.Account;
import net.binis.codegen.spring.query.executor.QueryOrderer;
import net.binis.codegen.spring.query.executor.QueryExecutor;
import net.binis.codegen.spring.query.base.BaseQueryNameImpl;
import net.binis.codegen.spring.query.*;
import net.binis.codegen.spring.BaseEntityModifier;
import net.binis.codegen.modifier.Modifier;
import net.binis.codegen.modifier.Modifiable;
import net.binis.codegen.factory.CodeFactory;
import net.binis.codegen.creator.EntityCreator;
import net.binis.codegen.collection.EmbeddedCodeListImpl;
import net.binis.codegen.collection.EmbeddedCodeCollection;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import javax.annotation.processing.Generated;
import java.util.function.Function;
import java.util.Optional;
import java.util.List;
import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Generated(value = "UserEntityPrototype", comments = "User")
@EqualsAndHashCode(callSuper = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = UserEntity.TABLE_NAME)
@Table(name = UserEntity.TABLE_NAME, indexes = { @Index(name = "idx_" + UserEntity.TABLE_NAME + "_username", columnList = "username") })
public class UserEntity extends BaseEntity implements User, Previewable, Modifiable<User.Modify> {

    public static final String TABLE_NAME = "users";

    private static final long serialVersionUID = 8340909241398601047L;

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

    {
        CodeFactory.registerType(User.QuerySelect.class, UserQueryExecutorImpl::new, null);
        CodeFactory.registerType(User.class, UserEntity::new, null);
        CodeFactory.registerType(User.QueryName.class, UserQueryNameImpl::new, null);
    }

    public UserEntity() {
        super();
    }

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
        return new UserEntityModifyImpl();
    }

    protected class UserEntityModifyImpl extends BaseEntityModifier<User.Modify, User> implements User.Modify {

        protected UserEntityModifyImpl() {
            setObject(UserEntity.this);
        }

        public User.Modify accounts(List<Account> accounts) {
            UserEntity.this.accounts = accounts;
            return this;
        }

        public EmbeddedCodeCollection<Account.EmbeddedModify<Account.Modify>, Account, User.Modify> accounts() {
            if (UserEntity.this.accounts == null) {
                UserEntity.this.accounts = new java.util.ArrayList<>();
            }
            return new EmbeddedCodeListImpl<>(this, UserEntity.this.accounts, Account.class);
        }

        public User done() {
            return UserEntity.this;
        }

        public User.Modify email(String email) {
            UserEntity.this.email = email;
            return this;
        }

        public User.Modify firstName(String firstName) {
            UserEntity.this.firstName = firstName;
            return this;
        }

        public User.Modify id(Long id) {
            UserEntity.this.id = id;
            return this;
        }

        public User.Modify lastName(String lastName) {
            UserEntity.this.lastName = lastName;
            return this;
        }

        public User.Modify modified(OffsetDateTime modified) {
            UserEntity.this.modified = modified;
            return this;
        }

        public User.Modify password(String password) {
            UserEntity.this.password = password;
            return this;
        }

        public User.Modify username(String username) {
            UserEntity.this.username = username;
            return this;
        }
    }

    protected static class UserQueryExecutorImpl extends QueryExecutor implements User.QuerySelect, User.QueryFieldsStart {

        protected UserQueryExecutorImpl() {
            super(User.class, () -> new UserQueryNameImpl());
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
}
