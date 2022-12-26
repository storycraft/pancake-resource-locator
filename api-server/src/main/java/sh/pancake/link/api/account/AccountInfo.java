/*
 * Created on Mon Dec 26 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.account;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import sh.pancake.link.repository.account.Account;

@Data
@AllArgsConstructor
public class AccountInfo {
    private int id;

    private String email;

    private long createdAt;
    @Nullable
    private Long activatedAt;
    private long updatedAt;

    private boolean suspended;

    public static AccountInfo from(Account account) {
        return new AccountInfo(
            account.getId(), 
            account.getEmail(),
            account.getCreatedAt(), 
            account.getActivatedAt(),
            account.getUpdatedAt(),
            account.isSuspended()
        );
    }
}
