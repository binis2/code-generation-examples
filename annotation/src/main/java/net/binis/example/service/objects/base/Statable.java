package net.binis.example.service.objects.base;

public interface Statable<T extends Enum<?>> {
    T getState();
}
