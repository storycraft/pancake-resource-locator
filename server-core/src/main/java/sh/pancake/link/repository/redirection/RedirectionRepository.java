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

    /**
     * Get {@code Redirection} with name
     * 
     * @param name name of redirection
     * @return @{code Redirection} if redirection exists
     */
    @Nullable
    Redirection getWithName(String name);

    /**
     * Get {@code Redirection}
     * 
     * @param id id of redirection
     * @return @{code Redirection} if redirection exists
     */
    @Nullable
    Redirection get(long id);

    /**
     * Get every redirections with account id
     * 
     * @param accountId account id
     * @return {@code Redirection} array
     */
    Redirection[] getListOf(int accountId);

    /**
     * Delete redirection
     * 
     * @param id id of redirection
     * @return Affected row count
     */
    int delete(long id);

    /**
     * Add redirection
     * 
     * @param redirection redirection
     * @return Affected row count
     */
    int insert(Redirection redirection);
}
