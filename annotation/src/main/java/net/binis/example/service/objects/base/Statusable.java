package net.binis.example.service.objects.base;

public interface Statusable<T extends Enum<?>> {
    T getStatus();
}
