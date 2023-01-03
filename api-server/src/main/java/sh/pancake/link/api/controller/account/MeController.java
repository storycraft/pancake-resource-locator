/*
 * Created on Sun Dec 25 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import sh.pancake.link.api.APIResult;
import sh.pancake.link.api.account.AccountInfo;
import sh.pancake.link.api.auth.APIAuthenticator;
import sh.pancake.link.api.auth.AuthAccount;
import sh.pancake.link.api.auth.WithAuth;
import sh.pancake.link.api.service.AccountService;
import sh.pancake.link.repository.account.Account;

@RestController
@RequestMapping("account/me")
public class MeController {

    @Setter
    @Autowired
    private APIAuthenticator authenticator;

    @Setter
    @Autowired
    private AccountService accountService;

    @GetMapping
    @WithAuth
    public APIResult<AccountInfo> getInfo(
        @AuthAccount Account account
    ) {
        return APIResult.success(AccountInfo.from(account));
    }
}
