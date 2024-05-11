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
            user = User.builder()
                    .id(1L)
                    .username("eunaldi")
                    .password("121221")
                    .email("emree.unaldi@gmail.com")
                    .firstName("Emre")
                    .lastName("Ünaldı")
                    .phoneNumber("05078711189")
                    .birthDate(LocalDate.of(2001, 1, 1))
                    .gender(Gender.MALE)
                    .build();
        }

        return user;
    }

    public UserUpdateRequest getUserUpdateRequest() {
        if (userUpdateRequest == null) {
            userUpdateRequest = UserUpdateRequest.builder()
                    .id(1L)
                    .username("emreunaldi")
                    .password("23232323")
                    .email("emree.unaldi2@gmail.com")
                    .firstName("Emre 2")
                    .lastName("Ünaldı 2")
                    .phoneNumber("05078711122")
                    .birthDate(LocalDate.of(2000, 2, 2))
                    .gender(Gender.MALE)
                    .build();
        }

        return userUpdateRequest;
    }

    public UserSaveRequest getUserSaveRequest() {
        if (userSaveRequest == null) {
            userSaveRequest = UserSaveRequest.builder()
                    .username("eunaldi")
                    .password("121221")
                    .email("emree.unaldi@gmail.com")
                    .firstName("Emre")
                    .lastName("Ünaldı")
                    .phoneNumber("05078711189")
                    .birthDate(LocalDate.of(2001, 1, 1))
                    .gender(Gender.MALE)
                    .build();
        }

        return userSaveRequest;
    }

    public List<User> getUserList() {
        if (userList == null) {
            User userOne = User.builder()
                    .id(1L)
                    .username("eunaldi")
                    .password("121221")
                    .email("emree.unaldi@gmail.com")
                    .firstName("Emre")
                    .lastName("Ünaldı")
                    .phoneNumber("05078711189")
                    .birthDate(LocalDate.of(2001, 1, 1))
                    .gender(Gender.MALE)
                    .build();

            User userTwo = User.builder()
                    .id(2L)
                    .username("eunaldi")
                    .password("121221")
                    .email("emree.unaldi@gmail.com")
                    .firstName("Emre")
                    .lastName("Ünaldı")
                    .phoneNumber("05078711189")
                    .birthDate(LocalDate.of(2001, 1, 1))
                    .gender(Gender.MALE)
                    .build();

            userList = List.of(
                    userOne,
                    userTwo
            );
        }

        return userList;
    }
}

