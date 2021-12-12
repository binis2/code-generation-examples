package net.binis.example.core.objects.base;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@FunctionalInterface
public interface Identifiable {

    @JsonSerialize(using = ToStringSerializer.class)
    Long getId();

    static Identifiable of(Object object) {
        return (Identifiable) object;
    }

}
