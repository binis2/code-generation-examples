package net.binis.example.prototype.core.objects.base;

import net.binis.example.prototype.core.objects.UserEntityPrototype;
import net.binis.codegen.annotation.CodePrototype;

@CodePrototype(generateImplementation = false, interfaceSetters = false)
public interface UserablePrototype {

    UserEntityPrototype user();

}
