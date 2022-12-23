/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import sh.pancake.link.api.APIResult;
import sh.pancake.link.api.account.AccountLoginForm;
import sh.pancake.link.api.account.AccountRegisterForm;
import sh.pancake.link.api.service.AccountService;

@Log4j2
@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    @Setter
    private AccountService service;

    @PostMapping("register")
    public ResponseEntity<APIResult> register(@ModelAttribute AccountRegisterForm form) {
        log.trace(String.format("Registering account %s", form.getEmail()));
        service.register(form.getEmail(), form.getPassword());

        return ResponseEntity.ok(APIResult.success());
    }

    @PostMapping("login")
    public Object login(@ModelAttribute AccountLoginForm form) {
        return null;
    }

}
