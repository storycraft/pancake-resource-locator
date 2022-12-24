/*
 * Created on Sat Dec 24 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.account;

/**
 * Status code constants for account API
 */
public final class AccountStatusCode {
    /**
     * Account already exists (with same email address)
     */
    public final static int ALREADY_EXISTS = 1;

    /**
     * Login failed with some reasons (nonexistent account, incorrect password)
     */
    public final static int LOGIN_FAILED = 2;
}
