/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api.account;

import lombok.Data;

@Data
public class AccountRegisterForm {
    private String email;

    private String password;
}
