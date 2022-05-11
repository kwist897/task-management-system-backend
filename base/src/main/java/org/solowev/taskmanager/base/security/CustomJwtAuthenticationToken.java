package org.solowev.taskmanager.base.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;

import java.util.Collection;
import java.util.Map;

/**
 * Custom jwt authentication token with {@link SecurityUser} in principal
 */
public final class CustomJwtAuthenticationToken extends AbstractOAuth2TokenAuthenticationToken<Jwt> {

    public CustomJwtAuthenticationToken(Jwt token, SecurityUser principal, Collection<? extends GrantedAuthority> authorities) {
        super(token, principal, token, authorities);
        this.setAuthenticated(true);
    }

    @Override
    public Map<String, Object> getTokenAttributes() {
        return this.getToken().getClaims();
    }
}
