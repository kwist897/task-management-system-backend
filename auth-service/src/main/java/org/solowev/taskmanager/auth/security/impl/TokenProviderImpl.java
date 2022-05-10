package org.solowev.taskmanager.auth.security.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.solowev.taskmanager.auth.domain.User;
import org.solowev.taskmanager.auth.dto.response.AccessTokenResponseDto;
import org.solowev.taskmanager.auth.dto.response.RefreshTokenResponseDto;
import org.solowev.taskmanager.auth.exceptions.TokenException;
import org.solowev.taskmanager.auth.security.TokenProvider;
import org.solowev.taskmanager.auth.utils.KeystoreHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class TokenProviderImpl implements TokenProvider {
    private static final String JWK_KEY_ID = UUID.randomUUID().toString();

    /**
     * role defines that user has permission to obtain new access token by refresh token
     */
    private static final String REFRESH_ROLE = "ROLE_REFRESH_TOKEN";
    private final KeystoreHelper keystoreHelper;
    @Value("${app.baseUrl}")
    private String issuer;
    @Value("${app.jwt.expirationTime}")
    private Long accessExpirationTime;
    @Value("${app.jwt.refreshExpirationTime}")
    private Long refreshExpirationTime;

    @Override
    public AccessTokenResponseDto generateAccessToken(User user) {
        String jwtId = UUID.randomUUID().toString();
        Date expirationDate = new Date(new Date().getTime() + accessExpirationTime);

        JWTClaimsSet jwtClaimsSet = createJWTClaimsSet(user, jwtId, expirationDate, false);

        SignedJWT signedJWT = signJwt(jwtClaimsSet);

        return new AccessTokenResponseDto(jwtId, signedJWT.serialize(), expirationDate);
    }

    @Override
    public RefreshTokenResponseDto generateRefreshToken(User user) {
        String jwtId = UUID.randomUUID().toString();
        Date expirationDate = new Date(new Date().getTime() + refreshExpirationTime);

        JWTClaimsSet jwtClaimsSet = createJWTClaimsSet(user, jwtId, expirationDate, true);

        SignedJWT signedJWT = signJwt(jwtClaimsSet);

        return new RefreshTokenResponseDto(jwtId, signedJWT.serialize(), expirationDate);
    }

    @Override
    public JWK getJWK() {
        KeyPair keyPair = keystoreHelper.extractKeyPair();

        return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey((RSAPrivateKey) keyPair.getPrivate())
                .keyUse(KeyUse.SIGNATURE)
                .keyID(JWK_KEY_ID)
                .build();
    }

    /**
     * Signs the jwt with provided claims
     *
     * @param jwtClaimsSet ClaimSet
     * @return signed jwt
     */
    private SignedJWT signJwt(JWTClaimsSet jwtClaimsSet) {
        JWK jwk = getJWK();

        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .keyID(jwk.getKeyID())
                .build();

        SignedJWT signedJWT = new SignedJWT(jwsHeader, jwtClaimsSet);

        try {
            JWSSigner signer = new RSASSASigner(jwk.toRSAKey());
            signedJWT.sign(signer);

        } catch (JOSEException e) {
            log.error("Failed to generate token: {}", e.getMessage());
            throw new TokenException("An error occurred while generating token");
        }
        return signedJWT;
    }

    /**
     * creates token claims
     *
     * @param user           user
     * @param jwtId          token id
     * @param expirationDate token expiration date
     * @return JWTClaimsSet
     */
    private JWTClaimsSet createJWTClaimsSet(User user, String jwtId, Date expirationDate, boolean isRefreshToken) {
        String sub = user.getUsername();
        Map<String, Object> claims = createClaims(user, isRefreshToken);

        JWTClaimsSet.Builder claimBuilder = new JWTClaimsSet.Builder()
                .subject(sub)
                .issuer(issuer)
                .jwtID(jwtId)
                .issueTime(new Date())
                .expirationTime(expirationDate)
                .notBeforeTime(new Date());

        claims.forEach(claimBuilder::claim);

        return claimBuilder.build();
    }

    /**
     * Creates default claims
     *
     * @param user user
     * @return Map containing default claims
     */
    private Map<String, Object> createClaims(User user, boolean isRefreshToken) {
        Map<String, Object> map = new HashMap<>();
        List<String> roles;

        if (isRefreshToken) {
            roles = List.of(REFRESH_ROLE);
        } else {
            roles = user.getRoles().stream()
                    .map(role -> role.getRoleName().toString())
                    .toList();
        }

        map.put("userId", user.getId());
        map.put("userEmail", user.getEmail());
        map.put("accountType", user.getAccountType());
        map.put("authProvider", user.getAuthProvider());
        map.put("roles", roles);
        return map;
    }
}
