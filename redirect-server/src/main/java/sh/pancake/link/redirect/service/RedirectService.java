/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.redirect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import lombok.Setter;
import sh.pancake.link.repository.redirection.RedirectURL;
import sh.pancake.link.repository.redirection.RedirectionRepository;

@Service
public class RedirectService {
    @Autowired
    @Setter
    private RedirectionRepository repository;
    
    /**
     * Get redirection url of provided name
     * @param name name of redirection
     * @return {@code RedirectURL} object if exists
     */
    @Nullable
    public RedirectURL getRedirection(String name) {
        return repository.getURLWithName(name);
    }
}
