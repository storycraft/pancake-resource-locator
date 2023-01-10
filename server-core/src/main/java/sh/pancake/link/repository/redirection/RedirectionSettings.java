/*
 * Created on Tue Jan 03 2023
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.repository.redirection;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RedirectionSettings {
    private long updatedAt;

    @Nullable
    private Long expireAt;

    @Nullable
    private Long visitLimit;

    private boolean redirectionPage;

    private boolean userDisabled;
}
