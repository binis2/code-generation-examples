package net.binis.example.service.objects.base;

@FunctionalInterface
public interface Categorizable<T> {

    T getCategory();
}
