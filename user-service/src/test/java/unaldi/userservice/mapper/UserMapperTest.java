package unaldi.userservice.mapper;

import org.junit.jupiter.api.BeforeAll;
import unaldi.userservice.entity.User;
import unaldi.userservice.entity.dto.UserDTO;
import unaldi.userservice.entity.request.UserSaveRequest;
import unaldi.userservice.entity.request.UserUpdateRequest;
import unaldi.userservice.service.abstracts.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import unaldi.userservice.utils.FailTestMessages;
import unaldi.userservice.utils.ObjectFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
class UserMapperTest {
    private static User user;
    private static List<User> userList;
    private static UserSaveRequest userSaveRequest;
    private static UserUpdateRequest userUpdateRequest;

    @BeforeAll
    static void setUp() {
        user = ObjectFactory.getInstance().getUser();
        userList = ObjectFactory.getInstance().getUserList();
        userSaveRequest = ObjectFactory.getInstance().getUserSaveRequest();
        userUpdateRequest = ObjectFactory.getInstance().getUserUpdateRequest();
    }

    @Test
    void should_convertUserSaveRequestToUser() {
        User user = UserMapper.INSTANCE.convertToSaveUser(userSaveRequest);

        assertAll(
                () -> assertEquals(userSaveRequest.username(), user.getUsername(), FailTestMessages.USERNAME_MISMATCH),
                () -> assertEquals(userSaveRequest.password(), user.getPassword(), FailTestMessages.PASSWORD_MISMATCH),
                () -> assertEquals(userSaveRequest.email(), user.getEmail(), FailTestMessages.EMAIL_MISMATCH),
                () -> assertEquals(userSaveRequest.firstName(), user.getFirstName(), FailTestMessages.FIRST_NAME_MISMATCH),
                () -> assertEquals(userSaveRequest.lastName(), user.getLastName(), FailTestMessages.LAST_NAME_MISMATCH),
                () -> assertEquals(userSaveRequest.phoneNumber(), user.getPhoneNumber(), FailTestMessages.PHONE_NUMBER_MISMATCH),
                () -> assertEquals(userSaveRequest.birthDate(), user.getBirthDate(), FailTestMessages.BIRTH_DATE_MISMATCH),
                () -> assertEquals(userSaveRequest.gender(), user.getGender(), FailTestMessages.GENDER_MISMATCH)
        );
    }

    @Test
    void should_convertUserUpdateRequestToUser() {
        User user = UserMapper.INSTANCE.convertToUpdateUser(userUpdateRequest);

        assertAll(
                () -> assertEquals(userUpdateRequest.username(), user.getUsername(), FailTestMessages.USERNAME_MISMATCH),
                () -> assertEquals(userUpdateRequest.password(), user.getPassword(), FailTestMessages.PASSWORD_MISMATCH),
                () -> assertEquals(userUpdateRequest.email(), user.getEmail(), FailTestMessages.EMAIL_MISMATCH),
                () -> assertEquals(userUpdateRequest.firstName(), user.getFirstName(), FailTestMessages.FIRST_NAME_MISMATCH),
                () -> assertEquals(userUpdateRequest.lastName(), user.getLastName(), FailTestMessages.LAST_NAME_MISMATCH),
                () -> assertEquals(userUpdateRequest.phoneNumber(), user.getPhoneNumber(), FailTestMessages.PHONE_NUMBER_MISMATCH),
                () -> assertEquals(userUpdateRequest.birthDate(), user.getBirthDate(), FailTestMessages.BIRTH_DATE_MISMATCH),
                () -> assertEquals(userUpdateRequest.gender(), user.getGender(), FailTestMessages.GENDER_MISMATCH)
        );
    }

    @Test
    void should_convertUserToUserDTO() {
        UserDTO userDTO = UserMapper.INSTANCE.convertToUserDTO(user);

        assertAll(
                () -> assertEquals(user.getUsername(), userDTO.username(), FailTestMessages.USERNAME_MISMATCH),
                () -> assertEquals(user.getPassword(), userDTO.password(), FailTestMessages.PASSWORD_MISMATCH),
                () -> assertEquals(user.getEmail(), userDTO.email(), FailTestMessages.EMAIL_MISMATCH),
                () -> assertEquals(user.getFirstName(), userDTO.firstName(), FailTestMessages.FIRST_NAME_MISMATCH),
                () -> assertEquals(user.getLastName(), userDTO.lastName(), FailTestMessages.LAST_NAME_MISMATCH),
                () -> assertEquals(user.getPhoneNumber(), userDTO.phoneNumber(), FailTestMessages.PHONE_NUMBER_MISMATCH),
                () -> assertEquals(user.getBirthDate(), userDTO.birthDate(), FailTestMessages.BIRTH_DATE_MISMATCH),
                () -> assertEquals(user.getGender(), userDTO.gender(), FailTestMessages.GENDER_MISMATCH)
        );
    }

    @Test
    void should_convertListOfUsersToListOfUserDTOs() {
        List<UserDTO> userDTOList = UserMapper.INSTANCE.convertUserDTOs(userList);

        assertEquals(userList.get(0).getId(), userDTOList.get(0).id());
        assertEquals(userList.get(1).getId(), userDTOList.get(1).id());
    }
}
