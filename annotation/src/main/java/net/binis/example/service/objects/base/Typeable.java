package net.binis.example.service.objects.base;

@FunctionalInterface
public interface Typeable<T extends Enum<?>> {

    T getType();

}
