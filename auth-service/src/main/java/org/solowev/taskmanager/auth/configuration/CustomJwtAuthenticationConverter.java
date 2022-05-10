package org.solowev.taskmanager.auth.configuration;

import org.solowev.taskmanager.auth.security.CustomJwtAuthenticationToken;
import org.solowev.taskmanager.auth.security.SecurityUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.List;

public final class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private static final String USER_ID_CLAIM = "userId";
    private static final String USERNAME_CLAIM = "sub";
    private static final String EMAIL_CLAIM = "userEmail";
    private JwtGrantedAuthoritiesConverter jwtAuthoritiesConverter;

    public void setGrantedAuthoritiesConverter(JwtGrantedAuthoritiesConverter jwtAuthoritiesConverter) {
        this.jwtAuthoritiesConverter = jwtAuthoritiesConverter;
    }

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt source) {
        List<? extends GrantedAuthority> authorities =
                (List<? extends GrantedAuthority>) jwtAuthoritiesConverter.convert(source);

        SecurityUser securityUser = createSecurityUser(source);

        return new CustomJwtAuthenticationToken(source, securityUser, authorities);
    }

    private SecurityUser createSecurityUser(Jwt jwt) {
        return SecurityUser.builder()
                .id(Long.valueOf(jwt.getClaimAsString(USER_ID_CLAIM)))
                .username(jwt.getClaimAsString(USERNAME_CLAIM))
                .email(jwt.getClaimAsString(EMAIL_CLAIM))
                .build();
    }
}
