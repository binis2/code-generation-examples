package net.binis.example.prototype.core.objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.binis.codegen.annotation.CodeClassAnnotations;
import net.binis.codegen.annotation.CodePrototype;
import net.binis.codegen.annotation.Default;
import net.binis.codegen.annotation.Ignore;
import net.binis.codegen.spring.BaseEntityModifier;
import net.binis.codegen.validation.annotation.ValidateNotBlank;
import net.binis.codegen.validation.annotation.ValidateNull;
import net.binis.example.core.objects.base.*;
import net.binis.example.core.objects.types.TransactionType;
import net.binis.example.prototype.core.base.BaseEntityPrototype;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@CodePrototype(
        classGetters = false,
        classSetters = false,
        interfaceSetters = false,
        implementationPackage = "net.binis.example.db.entity",
        baseModifierClass = BaseEntityModifier.class)
@Entity(name = TransactionEntityPrototype.TABLE_NAME)
@Table(name = TransactionEntityPrototype.TABLE_NAME, indexes = {
        @Index(name = "idx_" + TransactionEntityPrototype.TABLE_NAME + "_date", columnList = "timestamp"),
        @Index(name = "idx_" + TransactionEntityPrototype.TABLE_NAME + "_date_desc", columnList = "timestamp DESC"),
        @Index(name = "idx_" + TransactionEntityPrototype.TABLE_NAME + "_transaction_type", columnList = "transaction_type"),
        @Index(name = "idx_" + TransactionEntityPrototype.TABLE_NAME + "_external_id", columnList = "externalId")})
@FilterDef(name = "timestampAfter",
        parameters = {
                @ParamDef(name = "startDate", type = "java.time.OffsetDateTime")
        },
        defaultCondition = "timestamp >= :startDate")
public interface TransactionEntityPrototype extends BaseEntityPrototype, Taggable, Externalable<String>, Titleable, Descriptionable {

    long serialVersionUID = 2023385096577883838L;

    String TABLE_NAME = "transactions";

    @ValidateNotBlank
    String title();

    @Column(nullable = false)
    double amount();

    @Column(nullable = false)
    @ColumnDefault("current_timestamp")
    @Default("net.binis.example.core.tools.Time.now()")
    @ValidateNull(message = "'timestamp' can't be null!")
    OffsetDateTime timestamp();

    @Column(name = "transaction_type", nullable = false)
    @ValidateNull(message = "'type' can't be null!")
    TransactionType type();

    @OneToOne(targetEntity = AccountEntityPrototype.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonBackReference
    AccountEntityPrototype account();

    @OneToOne(targetEntity = AccountEntityPrototype.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "counterparty_id")
    @JsonBackReference
    AccountEntityPrototype counterparty();

    @Transient
    <T> T tag();

    @Ignore(forInterface = true)
    @Transient
    default String getPreview() {
        return timestamp().format(DateTimeFormatter.ISO_OFFSET_DATE) +
                " (" + title() + " for $" + amount() + ")" +
                " -> account: " + (Objects.nonNull(account()) ? ((Previewable) account()).getPreview() : "no account");
    }

    @CodeClassAnnotations
    @Data
    @EqualsAndHashCode(callSuper = true)
    @ToString(onlyExplicitlyIncluded = true)
    abstract class ClassAnnotations extends BaseClassAnnotations implements Previewable {

    }
}
