/*
 * Created on Sat Dec 24 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.account;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Contains credential tokens can be used in api request
 */
@Data
@AllArgsConstructor
public class AccountCredential {
    /**
     * Access token
     */
    private String accessToken;

    /**
     * Refresh token
     */
    private String refreshToken;

    /**
     * Expire time in seconds
     */
    private int expiresIn;
}
