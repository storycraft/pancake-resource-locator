/*
 * Created on Sat Dec 24 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Setter;
import sh.pancake.link.api.APIResult;
import sh.pancake.link.api.APIStatusCode;
import sh.pancake.link.api.account.AccountCredential;
import sh.pancake.link.api.auth.RefreshTokenManager;
import sh.pancake.link.api.service.AccountService;
import sh.pancake.link.api.service.OAuthService;

@Controller
@RequestMapping("oauth")
public class OAuthController {

    @Setter
    @Autowired
    private RefreshTokenManager refreshTokenManager;

    @Setter
    @Autowired
    private AccountService accountService;

    @Setter
    @Autowired
    private OAuthService oAuthService;

    @PostMapping("refresh")
    public APIResult<AccountCredential> refresh(@RequestParam("refresh_token") String refreshToken) {
        Integer accountId = refreshTokenManager.verify(refreshToken);
        if (accountId == null || accountService.isSuspended(accountId)) {
            return APIResult.error(APIStatusCode.FAILED);
        }

        return APIResult.success(oAuthService.issue(accountId));
    }
}
