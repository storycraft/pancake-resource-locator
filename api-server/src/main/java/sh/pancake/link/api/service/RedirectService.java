/*
 * Created on Sat Dec 24 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import lombok.Setter;
import sh.pancake.link.repository.redirection.RedirectURL;
import sh.pancake.link.repository.redirection.Redirection;
import sh.pancake.link.repository.redirection.RedirectionRepository;

@Service
public class RedirectService {
    @Autowired
    @Setter
    private RedirectionRepository repository;
    
    /**
     * Get redirect url of provided name
     * @param name name of redirection
     * @return {@code RedirectURL} object if exists
     */
    @Nullable
    public RedirectURL getRedirectURL(String name) {
        return repository.getURLWithName(name);
    }

    /**
     * Get {@code Redirection} of name
     *
     * @param name name of redirection
     * @return {@code Redirection} if exists
     */
    @Nullable
    public Redirection getRedirection(String name) {
        return repository.getWithName(name);
    }

    /**
     * Get {@code Redirection} of id
     *
     * @param id id of redirection
     * @return {@code Redirection} if exists
     */
    @Nullable
    public Redirection getRedirection(long id) {
        return repository.get(id);
    }
}
