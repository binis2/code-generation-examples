/*Generated code by Binis' code generator.*/
package net.binis.example.db.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.CreatedBy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ColumnDefault;
import net.binis.example.core.base.BaseInterface;
import lombok.ToString;
import javax.persistence.*;
import javax.annotation.processing.Generated;
import java.time.OffsetDateTime;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

@Generated(value = "BaseEntityPrototype", comments = "BaseInterface")
@ToString(onlyExplicitlyIncluded = true)
@MappedSuperclass
@Access(AccessType.FIELD)
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements BaseInterface {

    private static final long serialVersionUID = 2715722432365286225L;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @ColumnDefault("current_timestamp")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    protected OffsetDateTime created;

    @CreatedBy
    @Column(updatable = false)
    protected String createdBy;

    @Id
    @GenericGenerator(name = "seq_id", strategy = "net.binis.example.db.generator.IdGenerator")
    @GeneratedValue(generator = "seq_id")
    @Column(name = "id", nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonSerialize(using = ToStringSerializer.class)
    @ToString.Include
    protected Long id;

    @LastModifiedDate
    @Column(nullable = false)
    @ColumnDefault("current_timestamp")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    protected OffsetDateTime modified;

    @LastModifiedBy
    protected String modifiedBy;

    public BaseEntity() {
    }

    public <T> T as(Class<T> cls) {
        return cls.cast(this);
    }

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @ColumnDefault("current_timestamp")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    public OffsetDateTime getCreated() {
        return created;
    }

    @CreatedBy
    @Column(updatable = false)
    public String getCreatedBy() {
        return createdBy;
    }

    public Long getId() {
        return id;
    }

    @LastModifiedDate
    @Column(nullable = false)
    @ColumnDefault("current_timestamp")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    public OffsetDateTime getModified() {
        return modified;
    }

    @LastModifiedBy
    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setModified(OffsetDateTime modified) {
        this.modified = modified;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
