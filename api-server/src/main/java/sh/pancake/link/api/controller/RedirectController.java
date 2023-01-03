/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import sh.pancake.link.api.APIResult;
import sh.pancake.link.api.APIStatusCode;
import sh.pancake.link.api.auth.APIAuthenticator;
import sh.pancake.link.api.auth.AuthAccount;
import sh.pancake.link.api.auth.WithAuth;
import sh.pancake.link.api.redirect.NewRedirectionForm;
import sh.pancake.link.api.redirect.RedirectStatusCode;
import sh.pancake.link.api.redirect.RedirectionInfo;
import sh.pancake.link.api.redirect.RedirectionUpdateForm;
import sh.pancake.link.api.service.AccountService;
import sh.pancake.link.api.service.RedirectService;
import sh.pancake.link.api.service.VisitlogService;
import sh.pancake.link.repository.account.Account;
import sh.pancake.link.repository.redirection.RedirectURL;
import sh.pancake.link.repository.redirection.Redirection;
import sh.pancake.link.repository.redirection.RedirectionSettings;

/**
 * Redirect API controller
 * 
 * @see RedirectStatusCode
 */
@RestController
@RequestMapping("redirect")
public class RedirectController {

    @Setter
    @Autowired
    private RedirectService service;

    @Setter
    @Autowired
    private APIAuthenticator authenticator;

    @Setter
    @Autowired
    private AccountService accountService;

    @Setter
    @Autowired
    private VisitlogService visitlogService;
    

    @GetMapping
    @WithAuth
    public APIResult<RedirectionInfo[]> getRedirections(
        @AuthAccount Account account
    ) {
        return APIResult.success(service.getRedirections(account.getId()));
    }

    @PostMapping
    @WithAuth
    public APIResult<Long> newRedirection(
        @AuthAccount Account account,
        @ModelAttribute NewRedirectionForm form
    ) {
        long now = Instant.now().toEpochMilli();

        Redirection redirection = new Redirection(
            0,
            account.getId(),
            form.getName(),
            form.getUrl(),
            now,
            now,
            form.getExpireAt(),
            form.getVisitLimit(),
            form.isRedirectionPage(),
            false
        );

        if (!service.add(redirection)) {
            return APIResult.error(APIStatusCode.FAILED);
        }

        return APIResult.success(redirection.getId());
    }

    @GetMapping("{id}")
    @WithAuth
    public APIResult<RedirectionInfo> redirection(
        @PathVariable("id") long id,
        @AuthAccount Account account
    ) {
        Redirection redirection = service.get(account.getId(), id);

        if (redirection == null) {
            return APIResult.error(RedirectStatusCode.NOT_FOUND);
        }

        return APIResult.success(RedirectionInfo.from(redirection));
    }

    @PutMapping("{id}/visits")
    @WithAuth
    public APIResult<Long> getVisitCount(
        @PathVariable("id") long id,
        @AuthAccount Account account
    ) {
        Redirection redirection = service.get(account.getId(), id);

        if (redirection == null) {
            return APIResult.error(RedirectStatusCode.NOT_FOUND);
        }

        Long visitCount = visitlogService.getVisitCount(id);
        if (visitCount == null) {
            return APIResult.error(RedirectStatusCode.NOT_FOUND);
        }

        return APIResult.success(visitCount);
    }

    @PutMapping("{id}/settings")
    @WithAuth
    public APIResult<Void> updateRedirection(
        @PathVariable("id") long id,
        @AuthAccount Account account,
        @ModelAttribute RedirectionUpdateForm form
    ) {
        if (!service.updateSettings(
            account.getId(),
            id,
            new RedirectionSettings(
                Instant.now().toEpochMilli(),
                form.getExpireAt(),
                form.getVisitLimit(),
                form.isRedirectionPage(),
                form.isUserDisabled()
            )
        )) {
            return APIResult.error(RedirectStatusCode.NOT_FOUND);
        }

        return APIResult.success();
    }

    @DeleteMapping("{id}")
    @WithAuth
    public APIResult<Void> deleteRedirection(
        @PathVariable("id") long id,
        @AuthAccount Account account
    ) {
        if (!service.delete(account.getId(), id)) {
            return APIResult.error(APIStatusCode.FAILED);
        }

        return APIResult.success();
    }

    @GetMapping("named/{name}")
    @WithAuth
    public APIResult<RedirectionInfo> redirectionByName(
        @PathVariable("name") String name,
        @AuthAccount Account account
    ) {
        Redirection redirection = service.getWithName(account.getId(), name);

        if (redirection == null) {
            return APIResult.error(RedirectStatusCode.NOT_FOUND);
        }

        return APIResult.success(RedirectionInfo.from(redirection));
    }

    @GetMapping("named/{name}/url")
    @WithAuth
    public APIResult<String> redirectURLByName(
        @PathVariable("name") String name,
        @AuthAccount Account account
    ) {
        RedirectURL url = service.getRedirectURL(account.getId(), name);

        if (url == null) {
            return APIResult.error(RedirectStatusCode.NOT_FOUND);
        }

        return APIResult.success(url.getUrl());
    }
}
