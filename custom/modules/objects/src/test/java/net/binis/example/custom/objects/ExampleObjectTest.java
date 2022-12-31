package net.binis.example.custom.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExampleObjectTest {

    @Test
    void test() {
        var object = ExampleObject.create().example("example").done();

        assertEquals("example", object.getExample());

        object.helloWorld();

        assertEquals("Hello World!", object.getExample());
    }

}
