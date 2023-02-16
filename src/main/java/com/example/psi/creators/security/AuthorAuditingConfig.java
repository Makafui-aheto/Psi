package com.example.psi.creators.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuthorAuditingConfig {

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return new SpringAuthorSecurityAuditAwareImpl();
    }

}
