package net.binis.example.prototype.core.objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.binis.codegen.annotation.CodeConstant;
import net.binis.codegen.annotation.CodePrototype;
import net.binis.codegen.annotation.Default;
import net.binis.codegen.annotation.Ignore;
import net.binis.codegen.spring.BaseEntityModifier;
import net.binis.example.core.objects.base.*;
import net.binis.example.core.objects.types.AccountType;
import net.binis.example.prototype.core.base.BaseEntityPrototype;
import net.binis.example.prototype.core.objects.base.UserablePrototype;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.List;

import static java.util.Objects.nonNull;

@CodePrototype(
        classGetters = false,
        classSetters = false,
        interfaceSetters = false,
        implementationPackage = "net.binis.example.db.entity",
        baseModifierClass = BaseEntityModifier.class)
@Entity(name = AccountEntityPrototype.TABLE_NAME)
@Table(name = AccountEntityPrototype.TABLE_NAME, indexes = {
        @Index(name = "idx_" + AccountEntityPrototype.TABLE_NAME + "_account_number", columnList = "accountNumber"),
        @Index(name = "idx_" + AccountEntityPrototype.TABLE_NAME + "_active", columnList = "active"),
        @Index(name = "idx_" + AccountEntityPrototype.TABLE_NAME + "_external", columnList = "externalId", unique = true)}
)
public interface AccountEntityPrototype extends BaseEntityPrototype, Typeable<AccountType>, Externalable<String>, UserablePrototype, Nameable, Descriptionable {

    @CodeConstant(isPublic = false)
    long serialVersionUID = -640283484493905851L;

    String TABLE_NAME = "accounts";

    String accountNumber();

    @Column(nullable = false)
    @ColumnDefault("0.0")
    double balance();

    @Column(nullable = false)
    @ColumnDefault("0.0")
    double available();

    @Column(nullable = false)
    @ColumnDefault("0.0")
    double pending();

    @ColumnDefault("true")
    @Default("true")
    boolean active();

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "account_type", nullable = false)
    @Default("AccountType.CHECKING")
    AccountType type();

    @OneToOne(targetEntity = UserEntityPrototype.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    UserEntityPrototype user();

    @OneToMany(targetEntity = TransactionEntityPrototype.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonManagedReference
    List<TransactionEntityPrototype> transactions();

    @Ignore(forInterface = true)
    @Transient
    default String getPreview() {
        return getName() + " [" + accountNumber() + "] (user: " + (nonNull(user()) ? user().username() : "no user") + ")" ;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @ToString(onlyExplicitlyIncluded = true)
    abstract class ClassAnnotations extends BaseClassAnnotations implements Previewable {

    }

}
