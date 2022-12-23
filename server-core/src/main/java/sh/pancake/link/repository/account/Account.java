/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */

package sh.pancake.link.repository.account;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    /**
     * Unique account identifier used in database
     */
    private int id;

    /**
     * Email address
     */
    private String email;

    /**
     * Type of account
     */
    private String accountType;

    /**
     * Account credential
     */
    private String credential;

    /**
     * Creation date of account
     */
    private long createdAt;

    /**
     * Account activated date
     */
    @Nullable
    private Long activatedAt;

    /**
     * Last info updated time
     */
    private long updatedAt;

    /**
     * Suspended
     */
    private boolean suspended;
}
