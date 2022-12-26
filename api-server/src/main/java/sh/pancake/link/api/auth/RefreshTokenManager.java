/*
 * Created on Mon Dec 26 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.auth;

import com.auth0.jwt.algorithms.Algorithm;

public class RefreshTokenManager extends TokenManager {
    public RefreshTokenManager(String secret, int expireTime) {
        super(Algorithm.HMAC256(secret), "refresh_token", expireTime);
    }
}
