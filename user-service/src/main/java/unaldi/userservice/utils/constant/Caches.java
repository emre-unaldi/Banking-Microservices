package unaldi.userservice.utils.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Caches {
    public static final String USER_CACHE = "user";
    public static final String USERS_CACHE = "users";
    public static final String KEY_PREFIX = "user-service:";
}
