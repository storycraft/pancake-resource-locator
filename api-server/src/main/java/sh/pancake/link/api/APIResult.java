/*
 * Created on Sat Dec 24 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Common API result container
 */
@Data
@AllArgsConstructor
public class APIResult {

    /**
     * Result code of api. 0 value always means success.
     * <p>
     * See corresponding document for other status codes.
     */
    private int code;

    public static APIResult success() {
        return new APIResult(0);
    }
}
