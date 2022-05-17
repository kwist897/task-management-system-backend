package org.solowev.taskmanager.project.configuration;

import lombok.RequiredArgsConstructor;
import org.solowev.taskmanager.base.configuration.CustomJwtAuthenticationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final AccessDecisionManager accessDecisionManager;

    private final CustomJwtAuthenticationConverter customJwtAuthenticationConverter;

    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //@formatter:off
        http
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .cors().configurationSource(corsConfigurationSource)
                .and()
                    .csrf().disable()
                .authorizeRequests()
                    .anyRequest().hasRole("USER")
                .accessDecisionManager(accessDecisionManager)
                .and()
                    .oauth2ResourceServer()
                        .jwt(jwt ->
                                jwt.jwtAuthenticationConverter(customJwtAuthenticationConverter));
        //@formatter:on
        return http.build();
    }
}
