package net.binis.example.core.objects.base;

public interface Statable<T extends Enum<?>> {
    T getState();
}
