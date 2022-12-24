/*
 * Created on Sat Dec 24 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.controller.account;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Setter;

@Controller
@RequestMapping("account/google")
public class GoogleLoginController {

    private static final String LOGIN_URL = "https://accounts.google.com";

    @Setter
    private String clientId;

    @GetMapping("login")
    public ResponseEntity<Void> login() {
        HttpHeaders header = new HttpHeaders();

        header.setLocation(
            UriComponentsBuilder.fromUriString(LOGIN_URL)
            .queryParam("client_id", clientId)
            .queryParam("redirect_uri", "") // TODO
            .queryParam("response_type", "token")
            .queryParam("scope", "email,openid")
            .build()
            .toUri()
        );

        return new ResponseEntity<>(header, HttpStatus.SEE_OTHER);
    }

    @GetMapping("redirect")
    public void redirect() {

    }
}
