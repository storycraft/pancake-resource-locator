/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.repository.redirection;

import lombok.Data;

@Data
public class Redirection {
    private long id;

    private int accountId;

    private String name;
    private String url;

    private long createdAt;
    private long updatedAt;

    private long expireAt;
    private int visitLimit;

    private boolean redirectionPage;

    private boolean userDisabled;
}
