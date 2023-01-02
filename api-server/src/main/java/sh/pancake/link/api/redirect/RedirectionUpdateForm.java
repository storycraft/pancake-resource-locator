/*
 * Created on Tue Jan 03 2023
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.redirect;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class RedirectionUpdateForm {
    @Nullable
    private Long expireAt;

    @Nullable
    private Long visitLimit;

    private boolean redirectionPage;

    private boolean userDisabled;
}
