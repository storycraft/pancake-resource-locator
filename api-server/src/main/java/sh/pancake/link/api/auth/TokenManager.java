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

import lombok.Getter;

/**
 * Issue new JWT token and verify it
 */
public class TokenManager {

    @Getter
    private int expireTime;

    private String tokenType;

    private Algorithm algorithm;
    private JWTVerifier verifier;

    public TokenManager(Algorithm algorithm, String tokenType, int expireTime) {
        this.tokenType = tokenType;
        this.expireTime = expireTime;

        this.algorithm = algorithm;
        this.verifier = JWT.require(algorithm).acceptExpiresAt(expireTime).build();
    }

    /**
     * Issue new token
     * 
     * @param accountId Account id
     * @return New token with expire time
     */
    public String issue(int accountId) {
        return JWT.create()
            .withExpiresAt(Date.from(Instant.now().plusSeconds(expireTime)))
            .withClaim("token_type", tokenType)
            .withClaim("account_id", accountId)
            .sign(algorithm);
    }

    /**
     * Verify token
     * 
     * @param token token
     * @return Extracted account id if provided access token is valid
     */
    @Nullable
    public Integer verify(String token) {
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
}
