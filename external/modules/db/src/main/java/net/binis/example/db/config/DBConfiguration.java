package net.binis.example.db.config;

import net.binis.example.core.resolver.IdentityResolver;
import net.binis.example.core.tools.Time;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider", dateTimeProviderRef = "dateTimeProvider")
public class DBConfiguration {

    @Bean
    public AuditorAware<String> auditorProvider(List<IdentityResolver> resolvers) {
        return () -> resolvers.stream().map(IdentityResolver::getIdentity).filter(Objects::nonNull).findFirst();
    }

    @Bean
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(Time.now());
    }

}
