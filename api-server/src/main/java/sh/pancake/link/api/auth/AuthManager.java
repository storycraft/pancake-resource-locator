/*
 * Created on Mon Dec 26 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.auth;

import java.time.Instant;
import java.util.Date;

import org.springframework.lang.Nullable;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import sh.pancake.link.api.account.AccountCredential;

/**
 * {@code AuthManager} can issue new {@code AccountCredential} for any account
 * and verify it
 */
public class AuthManager {

    private final static String ACCESS_TOKEN = "access_token";
    private final static String REFESH_TOKEN = "refresh_token";

    private int expireTime;

    private Algorithm algorithm;
    private JWTVerifier verifier;

    public AuthManager(String secret, int expireTime) {
        this.expireTime = expireTime;

        this.algorithm = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(algorithm).acceptExpiresAt(expireTime).build();
    }

    private String issueToken(int accountId, String tokenType) {
        return JWT.create()
            .withExpiresAt(Date.from(Instant.now().plusSeconds(expireTime)))
            .withClaim("token_type", tokenType)
            .withClaim("account_id", accountId)
            .sign(algorithm);
    }

    /**
     * Issue new {@code AccountCredential}
     * 
     * @param accountId Account id
     * @return New {@code AccountCredential} containing access token and refresh token with expire time
     */
    public AccountCredential issue(int accountId) {
        String accessToken = issueToken(accountId, ACCESS_TOKEN);
        String refreshToken = issueToken(accountId, REFESH_TOKEN);

        return new AccountCredential(accessToken, refreshToken, expireTime);
    }

    @Nullable
    private Integer verifyWithType(String token, String tokenType) {
        try {
            DecodedJWT jwtToken = verifier.verify(token);

            Claim typeClaim = jwtToken.getClaim("token_type");
            if (typeClaim == null || !tokenType.equals(typeClaim.asString())) {
                return null;
            }

            Claim idClaim = jwtToken.getClaim("account_id");
            if (idClaim == null) {
                return null;
            }

            return idClaim.asInt();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Verify access token
     * 
     * @param accessToken Access token
     * @return Extracted account id if provided access token is valid
     */
    @Nullable
    public Integer verifyAccessToken(String accessToken) {
        return verifyWithType(accessToken, ACCESS_TOKEN);
    }

    /**
     * Verify refresh token
     * 
     * @param refreshToken Refresh token
     * @return Extracted account id if provided refresh token is valid
     */
    @Nullable
    public Integer verifyRefreshToken(String refreshToken) {
        return verifyWithType(refreshToken, REFESH_TOKEN);
    }
}
