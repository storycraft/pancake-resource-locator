/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.redirection.repository.redirection;

import lombok.Data;

@Data
public class Redirection {
    private long id;

    private String name;

    private String url;
}
