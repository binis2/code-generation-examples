package net.binis.example.service.prototype.objects.example;

import net.binis.example.service.annotation.CodeExampleBuilder;
import net.binis.example.service.objects.base.Descriptionable;
import net.binis.example.service.objects.base.Nameable;

@CodeExampleBuilder
public interface ExampleObjectPrototype extends Nameable, Descriptionable {

    String example();

}
