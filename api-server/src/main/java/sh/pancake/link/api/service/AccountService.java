/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.service;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import sh.pancake.link.api.account.AccountCredential;

@Service
public class AccountService {

    /**
     * Register account
     *
     * @param email Email address of account
     * @param password Password of account
     * @return true on success, false if account already exists
     */
    public boolean register(String email, String password) {
        return false;
    }

    /**
     * Login with email and password
     *
     * @param email
     * @param password
     * @return {@link AccountCredential} on success
     */
    @Nullable
    public AccountCredential login(String email, String password) {
        return null;
    }
}
