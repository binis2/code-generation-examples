package net.binis.example.service.objects.base;

@FunctionalInterface
public interface Externalable<T> {

    T getExternalId();

}
