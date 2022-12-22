/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */

package sh.pancake.link.api.repository.account;

import lombok.Data;

@Data
public class Account {
    private int id;

    private String email;

    private String accountType;

    private String credential;

    private long createdAt;
    private long activatedAt;
    private long updatedAt;

    private boolean suspended;
}
