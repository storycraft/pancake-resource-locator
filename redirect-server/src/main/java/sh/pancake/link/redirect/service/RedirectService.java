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
import sh.pancake.link.repository.redirection.Redirection;
import sh.pancake.link.repository.redirection.RedirectionRepository;
import sh.pancake.link.repository.visitlog.VisitlogRepository;

@Service
public class RedirectService {
    @Autowired
    @Setter
    private RedirectionRepository repository;

    @Autowired
    @Setter
    private VisitlogRepository visitlogRepository;
    
    /**
     * Get valid redirection url of provided name
     * 
     * @param name name of redirection
     * @return {@code Redirection} object if exists and valid
     */
    @Nullable
    public Redirection getValid(String name) {
        Redirection redirection = repository.getWithName(name);

        if (redirection == null) {
            return null;
        }

        if (!checkValid(redirection)) {
            return null;
        }

        return redirection;
    }

    private boolean checkValid(Redirection redirection) {
        long now = System.currentTimeMillis();

        if (redirection.isUserDisabled()) {
            return false;
        }

        Long expireAt = redirection.getExpireAt();
        if (expireAt != null && expireAt > now) {
            return false;
        }

        Long visitLimit = redirection.getVisitLimit();
        if (visitLimit != null) {
            long visits = visitlogRepository.getVisitCount(redirection.getId());
            if (visitLimit > visits) {
                return false;
            }
        }

        return true;
    }


}
