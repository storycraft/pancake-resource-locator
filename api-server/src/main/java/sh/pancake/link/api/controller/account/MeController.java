/*
 * Created on Sun Dec 25 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import sh.pancake.link.api.APIResult;
import sh.pancake.link.api.APIStatusCode;
import sh.pancake.link.api.account.AccountInfo;
import sh.pancake.link.api.auth.APIAuthenticator;
import sh.pancake.link.api.service.AccountService;
import sh.pancake.link.repository.account.Account;
import sh.pancake.link.repository.redirection.Redirection;

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
    public APIResult<AccountInfo> getInfo(
        @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Integer accountId = authenticator.authenticate(authorization);
        if (accountId == null) {
            return APIResult.error(APIStatusCode.INVALID_CREDENTIAL);
        }

        Account account = accountService.get(accountId);
        if (account == null) {
            return APIResult.error(APIStatusCode.FAILED);
        }
        
        return APIResult.success(AccountInfo.from(account));
    }

    @PutMapping
    public APIResult<Void> updateInfo(
        @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @ModelAttribute AccountInfo info
    ) {
        Integer accountId = authenticator.authenticate(authorization);
        if (accountId == null) {
            return APIResult.error(APIStatusCode.INVALID_CREDENTIAL);
        }

        // TODO:: implement stub
        return APIResult.error(APIStatusCode.FAILED);
    }

    @GetMapping("me/redirections")
    public APIResult<Redirection[]> getMyRedirections(
        @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Integer accountId = authenticator.authenticate(authorization);
        if (accountId == null) {
            return APIResult.error(APIStatusCode.INVALID_CREDENTIAL);
        }
        
        return APIResult.success(accountService.getRedirections(accountId));
    }
}
