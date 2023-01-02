/*
 * Created on Tue Jan 03 2023
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import sh.pancake.link.repository.visitlog.VisitlogRepository;

@Service
public class VisitlogService {
    @Autowired
    @Setter
    private VisitlogRepository repository;
    
    /**
     * Get visit count of redirection
     *
     * @param redirectionId redirection id
     * @return visit count of redirection
     */
    public long getVisitCount(long redirectionId) {
        return repository.getVisitCount(redirectionId);
    }
}
