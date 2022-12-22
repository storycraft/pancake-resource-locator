/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.repository.visitlog;

import lombok.Data;

@Data
public class Visitlog {
    private long id;
    
    private long redirectionId;

    private String visitorAddress;

    private long visitedAt;
}
