/*
 * Created on Sat Dec 24 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sh.pancake.link.api.APIResult;
import sh.pancake.link.api.APIStatusCode;
import sh.pancake.link.api.account.AccountCredential;

@Controller
@RequestMapping("oauth")
public class OAuthController {
    @PostMapping("refresh")
    public APIResult<AccountCredential> refresh(@RequestParam("refresh_token") String refreshToken) {
        return APIResult.error(APIStatusCode.FAILED);
    }
}
