/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import lombok.Setter;
import sh.pancake.link.api.account.AccountCredential;
import sh.pancake.link.repository.account.Account;
import sh.pancake.link.repository.account.AccountRepository;

@Service
public class AccountService {

    @Setter
    @Autowired
    private AccountRepository repository;

    @Setter
    @Autowired
    private OAuthService oAuthService;

    /**
     * Register account
     *
     * @param email    Email address of account
     * @param password Password of account
     * @return true on success, false if account already exists
     */
    public boolean register(String email, String password) {
        long now = Instant.now().toEpochMilli();

        try {
            return repository.add(new Account(
                0,
                email,
                BCrypt.hashpw(password, BCrypt.gensalt()),
                now,
    
                // TODO:: add account activation
                now,
    
                now,
                false
            )) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public Account getValid(int accountId) {
        Account account = repository.get(accountId);

        if (account == null || account.isSuspended() || account.getActivatedAt() == null) {
            return null;
        }

        return account;
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
        Account account = repository.getWithEmail(email);

        if (account == null || account.getCredential() == null || account.isSuspended()) {
            return null;
        }

        if (!BCrypt.checkpw(password, account.getCredential())) {
            return null;
        }

        return oAuthService.issue(account.getId());
    }

    public Account get(int accountId) {
        return repository.get(accountId);
    }
}
