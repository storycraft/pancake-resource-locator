/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import lombok.Setter;
import sh.pancake.link.api.account.AccountCredential;
import sh.pancake.link.repository.account.AccountRepository;
import sh.pancake.link.repository.redirection.Redirection;
import sh.pancake.link.repository.redirection.RedirectionRepository;

@Service
public class AccountService {

    @Setter
    @Autowired
    private AccountRepository repository;

    @Setter
    @Autowired
    private RedirectionRepository redirectionRepository;

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
     * Try to login with email and password and issue {@code AccountCredential}
     *
     * @param email
     * @param password
     * @return {@link AccountCredential} on success
     */
    @Nullable
    public AccountCredential login(String email, String password) {
        return null;
    }

    /**
     * Get {@code Redirection} list of account
     *
     * @param id id of redirection
     * @return {@code Redirection} if exists
     */
    public Redirection[] getRedirections(int accountId) {
        return redirectionRepository.getListOf(accountId);
    }
}
