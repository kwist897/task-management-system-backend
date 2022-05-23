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

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final CustomJwtAuthenticationConverter customJwtAuthenticationConverter;

    private final AccessDecisionManager accessDecisionManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //@formatter:off
        http
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                    .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/auth/user/registration").permitAll()
                    .antMatchers("/auth/user/authenticate").permitAll()
                    .antMatchers("/auth/token/jwk/keys").permitAll()
                    .antMatchers("/auth/token/refresh").hasRole("REFRESH_TOKEN")
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
