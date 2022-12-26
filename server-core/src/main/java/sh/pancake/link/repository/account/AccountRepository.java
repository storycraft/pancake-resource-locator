/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.repository.account;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * Provide database operations for account table
 */
@Repository
public interface AccountRepository {
    /**
     * Add {@code Account} to table
     *
     * @param account Account data to add. {@link Account#id} field will be filled on success.
     * @return Affected row count. 1 on success
     */
    int add(Account account);

    /**
     * Get account with id
     * 
     * @param accountId Account id
     * @return {@link Account} if account exists
     */
    @Nullable
    Account get(int accountId);

    /**
     * Get account with email
     *
     * @param email Email address
     * @return {@link Account} if account exists
     */
    @Nullable
    Account getWithEmail(String email);
}
