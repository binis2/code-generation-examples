/*Generated code by Binis' code generator.*/
package net.binis.example.core.base;

import net.binis.codegen.objects.Projectable;
import javax.annotation.processing.Generated;
import java.time.OffsetDateTime;
import java.io.Serializable;

@Generated(value = "net.binis.example.prototype.core.base.BaseEntityPrototype", comments = "BaseEntity")
public interface BaseInterface extends Serializable, Projectable {
    OffsetDateTime getCreated();
    Long getId();
    OffsetDateTime getModified();

    interface Fields<T> {
        T id(Long id);
        T modified(OffsetDateTime modified);
    }
}
