/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import sh.pancake.link.api.APIResult;
import sh.pancake.link.api.APIStatusCode;
import sh.pancake.link.api.account.AccountCredential;
import sh.pancake.link.api.account.AccountLoginForm;
import sh.pancake.link.api.account.AccountRegisterForm;
import sh.pancake.link.api.account.AccountStatusCode;
import sh.pancake.link.api.service.AccountService;
import sh.pancake.link.repository.account.Account;

/**
 * Account API controller
 * 
 * @see AccountStatusCode
 */
@Log4j2
@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    @Setter
    private AccountService service;

    @PostMapping("register")
    public APIResult<Void> register(@ModelAttribute AccountRegisterForm form) {
        log.trace(String.format("Registering account %s", form.getEmail()));
        service.register(form.getEmail(), form.getPassword());

        return APIResult.success();
    }

    @PostMapping("login")
    public APIResult<AccountCredential> login(@ModelAttribute AccountLoginForm form) {
        log.trace(String.format("Login request with email %s", form.getEmail()));
        AccountCredential credential = service.login(form.getEmail(), form.getPassword());

        if (credential == null) {
            return APIResult.error(AccountStatusCode.LOGIN_FAILED);
        }

        return APIResult.success(credential);
    }

    @GetMapping("me")
    public APIResult<Account> getInfo() {
        // TODO:: implement stub
        return APIResult.error(APIStatusCode.FAILED);
    }

    @PutMapping("me")
    public APIResult<Account> updateInfo() {
        // TODO:: implement stub
        return APIResult.error(APIStatusCode.FAILED);
    }
}