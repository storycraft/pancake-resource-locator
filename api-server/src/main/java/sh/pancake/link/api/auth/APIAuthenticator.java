/*
 * Created on Mon Dec 26 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.auth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.lang.Nullable;

public class APIAuthenticator {
    private static final Pattern PATTERN = Pattern.compile("bearer (.+)", Pattern.CASE_INSENSITIVE);

    private AccessTokenManager manager;

    public APIAuthenticator(AccessTokenManager manager) {
        this.manager = manager;
    }

    @Nullable
    public Integer authenticate(String authorization) {
        Matcher matcher = PATTERN.matcher(authorization);
        if (!matcher.matches()) {
            return null;
        }

        String accessToken = matcher.group(1);

        return manager.verify(accessToken);
    }
}
