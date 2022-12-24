/*
 * Created on Sat Dec 24 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link.api;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Common API result container
 */
@Data
@AllArgsConstructor
public class APIResult<T> {

    /**
     * Result status of api. 0 value always means success.
     * <p>
     * See corresponding document for other status codes.
     */
    private int status;

    /**
     * Optional response data
     */
    @Nullable
    @JsonInclude(Include.NON_NULL)
    private T data;

    public static APIResult<Void> success() {
        return new APIResult<>(0, null);
    }

    public static <T> APIResult<T> success(T data) {
        return new APIResult<>(0, data);
    }

    public static <T> APIResult<T> error(int status) {
        return new APIResult<>(status, null);
    }
}
