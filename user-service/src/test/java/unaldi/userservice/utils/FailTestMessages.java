package unaldi.userservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FailTestMessages {
    public static final String USER_SAVE = "User save operation test failed !";
    public static final String USER_UPDATE = "User update operation test failed !";
    public static final String USER_DELETE = "User delete operation test failed !";
    public static final String USER_FIND = "User find operation test failed !";
    public static final String USERS_FIND = "User list fetch operation test failed !";

    public static final String USER_UPDATE_EXCEPTION = "UserNotFoundException was expected but not thrown during update operation";
    public static final String USER_DELETE_EXCEPTION = "UserNotFoundException was expected but not thrown during delete operation";
    public static final String USER_FIND_EXCEPTION = "UserNotFoundException was expected but not thrown during find operation";

    public static final String USERNAME_MISMATCH = "Username does not match";
    public static final String PASSWORD_MISMATCH = "Password does not match";
    public static final String EMAIL_MISMATCH = "Email does not match";
    public static final String FIRST_NAME_MISMATCH = "First name does not match";
    public static final String LAST_NAME_MISMATCH = "Last name does not match";
    public static final String PHONE_NUMBER_MISMATCH = "Phone number does not match";
    public static final String BIRTH_DATE_MISMATCH = "Birth date does not match";
    public static final String GENDER_MISMATCH = "Gender does not match";
}

