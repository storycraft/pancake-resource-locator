/*
 * Created on Mon Dec 26 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.redirect;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import sh.pancake.link.repository.redirection.Redirection;

@Data
@AllArgsConstructor
public class RedirectionInfo {
    private long id;

    private String name;
    private String url;

    private long createdAt;
    private long updatedAt;

    @Nullable
    private Long expireAt;

    @Nullable
    private Long visitLimit;

    private boolean redirectionPage;
    private boolean userDisabled;

    public static RedirectionInfo from(Redirection redirection) {
        return new RedirectionInfo(
            redirection.getId(), 
            redirection.getName(), 
            redirection.getUrl(), 
            redirection.getCreatedAt(), 
            redirection.getUpdatedAt(), 
            redirection.getExpireAt(), 
            redirection.getVisitLimit(), 
            redirection.isRedirectionPage(), 
            redirection.isUserDisabled()
        );
    }
}
