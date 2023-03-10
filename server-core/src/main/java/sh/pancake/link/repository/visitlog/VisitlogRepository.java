/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.repository.visitlog;

import org.springframework.stereotype.Repository;

/**
 * Provide database operations for visitlog table
 */
@Repository
public interface VisitlogRepository {
    /**
     * Add visitlog to database.
     * {@link Visitlog#id} will be filled on success
     * 
     * @param log {@code Visitlog} to add
     * @return Affected row count. 1 on success
     */
    int add(Visitlog log);

    /**
     * Get visit count of redirection
     *
     * @param redirectionId redirection id
     * @return Visit count of redirection
     */
    Long getVisitCount(long redirectionId);
}
