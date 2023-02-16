package com.example.psi.creators.security;

import com.example.psi.creators.model.Author;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

class SpringAuthorSecurityAuditAwareImpl implements AuditorAware<Long> {


    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        Author userDetails = (Author) authentication.getPrincipal();

        return Optional.ofNullable(userDetails.getId());
    }
}
