package org.solowev.taskmanager.auth.configuration;

import lombok.RequiredArgsConstructor;
import org.solowev.taskmanager.base.configuration.CustomJwtAuthenticationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final CustomJwtAuthenticationConverter customJwtAuthenticationConverter;

    private final CorsConfigurationSource corsConfigurationSource;

    private final AccessDecisionManager accessDecisionManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //@formatter:off
        http
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                    .cors().configurationSource(corsConfigurationSource)
                .and()
                    .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/user/registration").permitAll()
                    .antMatchers("/user/auth").permitAll()
                    .antMatchers("/token/jwk/keys").permitAll()
                    .antMatchers("/token/refresh").hasRole("REFRESH_TOKEN")
                    .anyRequest().hasRole("USER")
                .accessDecisionManager(accessDecisionManager)
                .and()
                    .oauth2ResourceServer()
                        .jwt(jwt ->
                                jwt.jwtAuthenticationConverter(customJwtAuthenticationConverter));

        //@formatter:on
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
