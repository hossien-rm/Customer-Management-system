package com.cms.config.swagger;

import com.cms.constant.ApplicationConstant;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    public static final String JWT_SCHEME = "jwtToken";


    @Bean
    public OpenAPI cmsOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("CUSTOMER MANAGER SYSTEM")
                        .description("Customer management API for customers, servers, factors, tickets, users and audit logs. "
                                + "Log in using POST /api/login, then paste jwtToken into Authorize. "
                                + "Use the raw token without a Bearer prefix. Access depends on the user's role."))
                .components(new Components()
                        .addSecuritySchemes(JWT_SCHEME, new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name(ApplicationConstant.JWT_HEADER)
                                .description("Raw jwtToken returned by POST /api/login  no Bearer prefix.")))
                .addSecurityItem(new SecurityRequirement().addList(JWT_SCHEME));
    }
}
