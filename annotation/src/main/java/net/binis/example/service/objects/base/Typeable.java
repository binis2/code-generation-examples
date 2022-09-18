package net.binis.example.service.objects.base;

import net.binis.codegen.objects.base.enumeration.CodeEnum;

@FunctionalInterface
public interface Typeable<T extends CodeEnum> {

    T getType();

}
