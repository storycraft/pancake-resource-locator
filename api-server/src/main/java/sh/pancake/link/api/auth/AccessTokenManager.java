/*
 * Created on Mon Dec 26 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.auth;

import com.auth0.jwt.algorithms.Algorithm;

public class AccessTokenManager extends TokenManager {
    public AccessTokenManager(String secret, int expireTime) {
        super(Algorithm.HMAC256(secret), "access_token", expireTime);
    }
}
