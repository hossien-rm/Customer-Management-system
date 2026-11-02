package com.cms.config.auditing;

import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class AuditingConfig implements AuditorAware<String> {

    private static final String ANONYMOUS_USER = "ANONYMOUS";

    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal())) {
            return Optional.of(ANONYMOUS_USER);
        }

        return Optional.of(authentication.getName());
    }
}
