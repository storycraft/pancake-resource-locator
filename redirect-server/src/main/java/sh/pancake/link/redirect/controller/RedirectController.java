/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.redirect.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import sh.pancake.link.repository.redirection.RedirectURL;
import sh.pancake.link.redirect.service.RedirectService;
import sh.pancake.link.redirect.service.VisitlogService;

@Controller
@Log4j2
public class RedirectController {

    @Autowired
    @Setter
    private RedirectService redirectService;

    @Autowired
    @Setter
    private VisitlogService visitlogService;

    @GetMapping(value = "/{name}")
    public void onRedirectPath(@PathVariable("name") String name, HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws IOException {
        RedirectURL redirection = redirectService.getRedirectURL(name);

        if (redirection == null) {
            log.trace(String.format("Redirection %s is not found", name));
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Redirect URL Not found");
        }

        log.trace(String.format("Redirect %s -> %s", name, redirection.getUrl()));
        visitlogService.addVisitlog(redirection.getId(), request.getRemoteAddr());

        httpServletResponse.sendRedirect(redirection.getUrl());
    }

}
