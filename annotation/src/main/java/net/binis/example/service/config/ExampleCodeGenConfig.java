package net.binis.example.service.config;

import lombok.extern.slf4j.Slf4j;
import net.binis.codegen.annotation.CodeConfiguration;

@Slf4j
@CodeConfiguration
public abstract class ExampleCodeGenConfig {

    public static void initialize() {
        log.info("Hello from Binis CodeGen config!");
    }

}
