/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import sh.pancake.link.api.APIResult;
import sh.pancake.link.api.redirect.RedirectStatusCode;
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

    @GetMapping("{name}")
    public APIResult<Redirection> redirection(@PathVariable("name") String name) {
        // TODO:: implement stub
        return APIResult.error(RedirectStatusCode.NOT_FOUND);
    }

    @GetMapping("{name}/url")
    public APIResult<String> redirectURL(@PathVariable("name") String name) {
        RedirectURL url = service.getRedirection(name);

        if (url == null) {
            return APIResult.error(RedirectStatusCode.NOT_FOUND);
        }

        return APIResult.success(url.getUrl());
    }
}
