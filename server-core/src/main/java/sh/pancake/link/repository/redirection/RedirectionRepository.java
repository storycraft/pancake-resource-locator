/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.repository.redirection;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * Provide database operations for redirection table
 */
@Repository
public interface RedirectionRepository {
    /**
     * Get redirect url of provided name from table
     *
     * @param name name of redirection
     * @return {@code RedirectURL} object if redirection exists
     */
    @Nullable
    RedirectURL getURLWithName(String name);
}
