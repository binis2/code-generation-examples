/*Generated code by Binis' code generator.*/
package net.binis.example.core.base;

import net.binis.example.core.objects.base.Identifiable;
import net.binis.codegen.objects.Projectable;
import javax.annotation.processing.Generated;
import java.time.OffsetDateTime;
import java.io.Serializable;

@Generated(value = "BaseEntityPrototype", comments = "BaseEntity")
public interface BaseInterface extends Serializable, Identifiable, Projectable {
    OffsetDateTime getCreated();
    OffsetDateTime getModified();

    interface Fields<T> {
        T id(Long id);
        T modified(OffsetDateTime modified);
    }
}
