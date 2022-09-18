package net.binis.example.service.prototype.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.ToString;
import net.binis.codegen.annotation.CodeConstant;
import net.binis.codegen.annotation.CodePrototype;
import net.binis.codegen.annotation.Ignore;
import net.binis.codegen.enrich.*;
import net.binis.example.service.objects.base.Identifiable;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@CodePrototype(
        base = true,
        interfaceName = "BaseInterface",
        interfaceSetters = false,
        implementationPackage = "net.binis.example.db.entity",
        enrichers = {AsEnricher.class, ModifierEnricher.class},
        inheritedEnrichers = {CreatorModifierEnricher.class, ModifierEnricher.class, QueryEnricher.class, ValidationEnricher.class, HibernateEnricher.class})
@MappedSuperclass
@Access(AccessType.FIELD)
@EntityListeners(AuditingEntityListener.class)
public interface BaseEntityPrototype extends Serializable, Identifiable {

    @CodeConstant(isPublic = false)
    long serialVersionUID = 2715722432365286225L;

    @Id
    @GenericGenerator(name = "seq_id", strategy = "net.binis.example.service.generator.IdGenerator")
    @GeneratedValue(generator = "seq_id")
    @Column(name = "id", nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonSerialize(using = ToStringSerializer.class)
    @ToString.Include
    Long id();

    @Ignore(forModifier = true)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    @ColumnDefault("current_timestamp")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    OffsetDateTime created();

    @LastModifiedDate
    @Column(nullable = false)
    @ColumnDefault("current_timestamp")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    OffsetDateTime modified();

    @Ignore(forInterface = true, forModifier = true)
    @CreatedBy
    @Column(updatable = false)
    String createdBy();

    @Ignore(forInterface = true, forModifier = true)
    @LastModifiedBy
    String modifiedBy();

    @ToString(onlyExplicitlyIncluded = true)
        class BaseClassAnnotations {

    }

}
