package net.binis.example.core.config;

import net.binis.example.core.resolver.IdentityResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class CoreConfiguration {

    @Bean
    @Order
    public IdentityResolver defaultIdentityResolver() {
        return () -> "<unknown>";
    }

}
