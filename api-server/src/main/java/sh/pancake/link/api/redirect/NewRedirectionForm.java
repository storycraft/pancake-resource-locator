/*
 * Created on Wed Dec 28 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.redirect;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class NewRedirectionForm {
    private String name;
    private String url;

    @Nullable
    private Long expireAt;
    @Nullable
    private Long visitLimit;
    
    private boolean redirectionPage;
}
