/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import sh.pancake.link.api.APIResult;
import sh.pancake.link.api.redirect.RedirectStatusCode;
import sh.pancake.link.api.service.RedirectService;
import sh.pancake.link.repository.redirection.RedirectURL;

@RestController
@RequestMapping("redirect")
public class RedirectController {

    @Setter
    @Autowired
    private RedirectService service;

    @GetMapping("url")
    public APIResult<String> redirectURL(@RequestParam("name") String name) {
        RedirectURL url = service.getRedirection(name);

        if (url == null) {
            return new APIResult<>(RedirectStatusCode.NOT_FOUND);
        }

        return APIResult.success(url.getUrl());
    }
}
