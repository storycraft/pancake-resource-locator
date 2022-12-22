/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.redirection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import lombok.Setter;
import sh.pancake.link.redirection.Redirection;
import sh.pancake.link.redirection.repository.RedirectionRepository;

@Service
public class RedirectionService {
    @Autowired
    @Setter
    private RedirectionRepository repository;
    
    /**
     * Get redirection url of provided name
     * @param name name of redirection
     * @return {@code Redirection} url to redirect if exists
     */
    @Nullable
    public String getRedirection(String name) {
        Redirection redirection = repository.getRedirection(name);

        if (redirection == null) {
            return null;
        }

        return redirection.getUrl();
    }
}
