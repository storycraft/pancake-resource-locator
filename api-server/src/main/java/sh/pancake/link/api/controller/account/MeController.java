/*
 * Created on Sun Dec 25 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.controller.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sh.pancake.link.api.APIResult;
import sh.pancake.link.api.APIStatusCode;
import sh.pancake.link.repository.account.Account;
import sh.pancake.link.repository.redirection.Redirection;

@RestController
@RequestMapping("account/me")
public class MeController {
    @GetMapping
    public APIResult<Account> getInfo() {
        // TODO:: implement stub
        return APIResult.error(APIStatusCode.FAILED);
    }

    @PutMapping
    public APIResult<Account> updateInfo() {
        // TODO:: implement stub
        return APIResult.error(APIStatusCode.FAILED);
    }

    @GetMapping("me/redirections")
    public APIResult<Redirection[]> getMyRedirections() {
        // TODO:: implement stub
        return APIResult.error(APIStatusCode.FAILED);
    }
}
