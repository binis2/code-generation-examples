package net.binis.example.core.objects.base;

public interface Statusable<T extends Enum<?>> {
    T getStatus();
}
