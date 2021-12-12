package net.binis.example.prototype.core.objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.binis.codegen.annotation.CodeConstant;
import net.binis.codegen.annotation.CodePrototype;
import net.binis.codegen.annotation.Ignore;
import net.binis.codegen.spring.BaseEntityModifier;
import net.binis.example.core.objects.base.Previewable;
import net.binis.example.db.entity.AccountEntity;
import net.binis.example.prototype.core.base.BaseEntityPrototype;

import javax.persistence.*;
import java.util.List;

@CodePrototype(
        interfaceSetters = false,
        implementationPackage = "net.binis.example.db.entity",
        baseModifierClass = BaseEntityModifier.class)
@Entity(name = UserEntityPrototype.TABLE_NAME)
@Table(name = UserEntityPrototype.TABLE_NAME, indexes = {
        @Index(name = "idx_" + UserEntityPrototype.TABLE_NAME + "_username", columnList = "username")
})
public interface UserEntityPrototype extends BaseEntityPrototype {

    @CodeConstant(isPublic = false)
    long serialVersionUID = 8340909241398601047L;

    String TABLE_NAME = "users";

    @ToString.Include
    @Column(unique = true)
    String username();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password();

    String firstName();
    String lastName();

    @Column(nullable = false, unique = true)
    String email();

    @OneToMany(targetEntity = AccountEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    List<AccountEntityPrototype> accounts();

    @Ignore(forInterface = true)
    @Transient
    default String getPreview() {
        return username() + " (" + firstName() + " " + lastName() + ")";
    }

    @EqualsAndHashCode(callSuper = true)
    @ToString(onlyExplicitlyIncluded = true)
    abstract class ClassAnnotations extends BaseClassAnnotations implements Previewable {

    }

}
