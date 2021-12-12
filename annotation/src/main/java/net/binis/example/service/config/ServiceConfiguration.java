package net.binis.example.service.config;

import net.binis.example.service.resolver.IdentityResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

@Configuration
public class ServiceConfiguration {

    @Bean
    @Order(1)
    public IdentityResolver identityResolver() {
        return () -> {
            var authentication = SecurityContextHolder.getContext().getAuthentication();
            if (Objects.nonNull(authentication)) {
                return authentication.getName();
            } else {
                return "<example-service>";
            }
        };
    }

}