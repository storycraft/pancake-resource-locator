/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import sh.pancake.link.api.APIResult;
import sh.pancake.link.api.APIStatusCode;
import sh.pancake.link.api.auth.APIAuthenticator;
import sh.pancake.link.api.redirect.RedirectStatusCode;
import sh.pancake.link.api.redirect.RedirectionInfo;
import sh.pancake.link.api.service.AccountService;
import sh.pancake.link.api.service.RedirectService;
import sh.pancake.link.repository.redirection.RedirectURL;
import sh.pancake.link.repository.redirection.Redirection;

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

    @PostMapping
    public APIResult<Long> newRedirection(
        @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @RequestParam("name") String name,
        @RequestParam("url") String url,
        @Nullable @RequestParam("expire_at") Long expireAt,
        @Nullable @RequestParam("visit_limit") Long visitLimit,
        @RequestParam("redirection_page") boolean redirectionPage
    ) {
        Integer accountId = authenticator.authenticate(authorization);
        if (accountId == null || accountService.isSuspended(accountId)) {
            return APIResult.error(APIStatusCode.INVALID_CREDENTIAL);
        }

        long now = Instant.now().toEpochMilli();

        Redirection redirection = new Redirection(
            0,
            accountId,
            name,
            url,
            now,
            now,
            expireAt,
            visitLimit,
            redirectionPage,
            false
        );

        if (!service.add(redirection)) {
            return APIResult.error(APIStatusCode.FAILED);
        }

        return APIResult.success(redirection.getId());
    }

    @GetMapping("{id}")
    public APIResult<RedirectionInfo> redirection(
        @PathVariable("id") long id,
        @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Integer accountId = authenticator.authenticate(authorization);
        if (accountId == null || accountService.isSuspended(accountId)) {
            return APIResult.error(APIStatusCode.INVALID_CREDENTIAL);
        }

        Redirection redirection = service.get(accountId, id);

        if (redirection == null) {
            return APIResult.error(RedirectStatusCode.NOT_FOUND);
        }

        if (!accountId.equals(redirection.getAccountId())) {
            return APIResult.error(RedirectStatusCode.NOT_FOUND);
        }

        return APIResult.success(RedirectionInfo.from(redirection));
    }

    @DeleteMapping("{id}")
    public APIResult<Void> deleteRedirection(
        @PathVariable("id") long id,
        @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Integer accountId = authenticator.authenticate(authorization);
        if (accountId == null || accountService.isSuspended(accountId)) {
            return APIResult.error(APIStatusCode.INVALID_CREDENTIAL);
        }

        if (!service.delete(accountId, id)) {
            return APIResult.error(APIStatusCode.FAILED);
        }

        return APIResult.success();
    }

    @GetMapping("named/{name}")
    public APIResult<RedirectionInfo> redirectionByName(
        @PathVariable("name") String name,
        @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Integer accountId = authenticator.authenticate(authorization);
        if (accountId == null || accountService.isSuspended(accountId)) {
            return APIResult.error(APIStatusCode.INVALID_CREDENTIAL);
        }

        Redirection redirection = service.getWithName(accountId, name);

        if (redirection == null) {
            return APIResult.error(RedirectStatusCode.NOT_FOUND);
        }

        return APIResult.success(RedirectionInfo.from(redirection));
    }

    @GetMapping("named/{name}/url")
    public APIResult<String> redirectURLByName(
        @PathVariable("name") String name,
        @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Integer accountId = authenticator.authenticate(authorization);
        if (accountId == null || accountService.isSuspended(accountId)) {
            return APIResult.error(APIStatusCode.INVALID_CREDENTIAL);
        }

        RedirectURL url = service.getRedirectURL(accountId, name);

        if (url == null) {
            return APIResult.error(RedirectStatusCode.NOT_FOUND);
        }

        return APIResult.success(url.getUrl());
    }
}
