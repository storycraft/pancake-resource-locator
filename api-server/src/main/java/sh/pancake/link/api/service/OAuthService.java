/*
 * Created on Mon Dec 26 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import sh.pancake.link.api.account.AccountCredential;
import sh.pancake.link.api.auth.AccessTokenManager;
import sh.pancake.link.api.auth.RefreshTokenManager;

@Service
public class OAuthService {

    @Setter
    @Autowired
    private AccessTokenManager accessTokenManager;

    @Setter
    @Autowired
    private RefreshTokenManager refreshTokenManager;

    public AccountCredential issue(int accountId) {
        int expiresIn = accessTokenManager.getExpireTime();
        String accessToken = accessTokenManager.issue(accountId);
        String newRefreshToken = refreshTokenManager.issue(accountId);

        return new AccountCredential(accessToken, newRefreshToken, expiresIn);
    }
}
