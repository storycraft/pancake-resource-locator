/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */

package sh.pancake.link.repository.account;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private int id;

    private String email;

    private String accountType;

    private String credential;

    private long createdAt;

    @Nullable
    private Long activatedAt;

    private long updatedAt;

    private boolean suspended;
}
