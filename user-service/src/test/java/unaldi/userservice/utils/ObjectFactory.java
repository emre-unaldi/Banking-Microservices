package unaldi.userservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import unaldi.userservice.entity.User;
import unaldi.userservice.entity.enums.Gender;
import unaldi.userservice.entity.request.UserSaveRequest;
import unaldi.userservice.entity.request.UserUpdateRequest;

import java.time.LocalDate;
import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectFactory {
    private static ObjectFactory instance;
    private User user;
    private UserUpdateRequest userUpdateRequest;
    private UserSaveRequest userSaveRequest;
    private List<User> userList;

    public static synchronized ObjectFactory getInstance() {
        if (instance == null) {
            instance = new ObjectFactory();
        }

        return instance;
    }

    public User getUser() {
        if (user == null) {
            user = new User(
                    1L,
                    "eunaldi",
                    "121221",
                    "emree.unaldi@gmail.com",
                    "Emre",
                    "Ünaldı",
                    "05078711189",
                    LocalDate.of(2001, 1, 1),
                    Gender.MALE
            );
        }

        return user;
    }

    public UserUpdateRequest getUserUpdateRequest() {
        if (userUpdateRequest == null) {
            userUpdateRequest = new UserUpdateRequest(
                    1L,
                    "emreunaldi",
                    "23232323",
                    "emre.unaldi2@gmail.com",
                    "Emre 2",
                    "Ünaldı 2",
                    "05078711122",
                    LocalDate.of(2000, 2, 2),
                    Gender.MALE
            );
        }

        return userUpdateRequest;
    }

    public UserSaveRequest getUserSaveRequest() {
        if (userSaveRequest == null) {
            userSaveRequest = new UserSaveRequest(
                    "eunaldi",
                    "121221",
                    "emree.unaldi@gmail.com",
                    "Emre",
                    "Ünaldı",
                    "05078711189",
                    LocalDate.of(2001, 1, 1),
                    Gender.MALE
            );
        }

        return userSaveRequest;
    }

    public List<User> getUserList() {
        if (userList == null) {
            userList = List.of(
                    new User(
                            1L,
                            "eunaldi",
                            "121221",
                            "emree.unaldi@gmail.com",
                            "Emre",
                            "Ünaldı",
                            "05078711189",
                            LocalDate.of(2001, 1, 1),
                            Gender.MALE
                    ),
                    new User(
                            2L,
                            "eunaldi",
                            "121221",
                            "emree.unaldi@gmail.com",
                            "Emre",
                            "Ünaldı",
                            "05078711189",
                            LocalDate.of(2001, 1, 1),
                            Gender.MALE
                    )
            );
        }

        return userList;
    }
}

