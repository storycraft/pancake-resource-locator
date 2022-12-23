/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.redirect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import sh.pancake.link.repository.visitlog.Visitlog;
import sh.pancake.link.repository.visitlog.VisitlogRepository;

@Service
public class VisitlogService {
    @Autowired
    @Setter
    private VisitlogRepository repository;

    /**
     * Add visitlog
     * 
     * @param redirectionId requested redirection id
     * @param visitorAddress ip address of request
     */
    public void addVisitlog(long redirectionId, String visitorAddress) {
        repository.addVisitlog(new Visitlog(redirectionId, visitorAddress, System.currentTimeMillis()));
    }
}
