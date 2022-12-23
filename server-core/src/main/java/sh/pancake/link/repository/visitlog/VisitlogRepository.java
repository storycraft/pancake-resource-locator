/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.repository.visitlog;

import org.springframework.stereotype.Repository;

@Repository
public interface VisitlogRepository {

    /**
     * Add visitlog to database
     * @param log {@code Visitlog} to add
     */
    void addVisitlog(Visitlog log);
}
