package net.binis.example.core.objects.base;

@FunctionalInterface
public interface Typeable<T extends Enum<?>> {

    T getType();

}
