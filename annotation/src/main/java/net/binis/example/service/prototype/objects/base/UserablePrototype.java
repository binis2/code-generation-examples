package net.binis.example.service.prototype.objects.base;

import net.binis.codegen.annotation.CodePrototype;
import net.binis.example.service.prototype.objects.UserEntityPrototype;

@CodePrototype(generateImplementation = false, interfaceSetters = false)
public interface UserablePrototype {

    UserEntityPrototype user();

}
