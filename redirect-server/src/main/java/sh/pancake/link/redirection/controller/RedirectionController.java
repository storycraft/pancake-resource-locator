/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.redirection.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import sh.pancake.link.redirection.service.RedirectionService;

@Controller
@Log4j2
public class RedirectionController {

    @Autowired
    @Setter
    private RedirectionService service;

    @GetMapping(value = "/{name}")
    public void onRedirectPath(@PathVariable("name") String name, HttpServletResponse httpServletResponse)
            throws IOException {
        String redirectURL = service.getRedirection(name);

        if (redirectURL == null) {
            log.trace(String.format("Redirection %s is not found", name));
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Redirect URL Not found");
        }

        log.trace(String.format("Redirect %s -> %s", name, redirectURL));
        httpServletResponse.sendRedirect(redirectURL);
    }

}
