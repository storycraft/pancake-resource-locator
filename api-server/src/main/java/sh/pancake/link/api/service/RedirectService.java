/*
 * Created on Sat Dec 24 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.service;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import lombok.Setter;
import sh.pancake.link.api.redirect.RedirectionInfo;
import sh.pancake.link.repository.redirection.RedirectURL;
import sh.pancake.link.repository.redirection.Redirection;
import sh.pancake.link.repository.redirection.RedirectionRepository;
import sh.pancake.link.repository.redirection.RedirectionSettings;

@Service
public class RedirectService {
    @Autowired
    @Setter
    private RedirectionRepository repository;
    
    /**
     * Get redirect url of provided name
     * 
     * @param accountId Account id
     * @param name name of redirection
     * @return {@code RedirectURL} object if exists
     */
    @Nullable
    public RedirectURL getRedirectURL(int accountId, String name) {
        RedirectURL url = repository.getURLWithName(name);

        if (url == null || url.getAccountId() != accountId) {
            return null;
        }

        return url;
    }

    /**
     * Get {@code Redirection} with name
     *
     * @param accountId Account id
     * @param name name of redirection
     * @return {@code Redirection} if exists
     */
    @Nullable
    public Redirection getWithName(int accountId, String name) {
        Redirection redirection = repository.getWithName(name);

        if (redirection == null || redirection.getAccountId() != accountId) {
            return null;
        }

        return redirection;
    }

    public boolean delete(int accountId, long id) {
        Redirection redirection = repository.get(id);

        if (redirection == null || redirection.getAccountId() != accountId) {
            return false;
        }

        return repository.delete(id) > 0;
    }

    /**
     * Get {@code Redirection} with id
     *
     * @param accountId Account id
     * @param id id of redirection
     * @return {@code Redirection} if exists
     */
    @Nullable
    public Redirection get(int accountId, long id) {
        Redirection redirection = repository.get(id);

        if (redirection == null || redirection.getAccountId() != accountId) {
            return null;
        }

        return redirection;
    }

    /**
     * Add redirection
     *
     * @param redirection Redirection
     * @return true on success
     */
    public boolean add(Redirection redirection) {
        try {
            return repository.insert(redirection) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Update redirection settings
     * 
     * @param accountId account id
     * @param id redirection id
     * @param settings redirection settings
     * @return true on success
     */
    public boolean updateSettings(int accountId, long id, RedirectionSettings settings) {
        Redirection redirection = repository.get(id);

        if (redirection == null || redirection.getAccountId() != accountId) {
            return false;
        }
        
        return repository.update(id, settings) > 0;
    }

    /**
     * Get {@code Redirection} list of account
     *
     * @param id id of redirection
     * @return array of {@code RedirectionInfo}
     */
    public RedirectionInfo[] getRedirections(int accountId) {
        return Stream.of(repository.getListOf(accountId))
            .map((redirection) -> RedirectionInfo.from(redirection))
            .toArray(RedirectionInfo[]::new);
    }
}
