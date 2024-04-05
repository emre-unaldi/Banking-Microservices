package unaldi.userservice.mapper;

import unaldi.userservice.entity.User;
import unaldi.userservice.entity.dto.UserDTO;
import unaldi.userservice.entity.enums.Gender;
import unaldi.userservice.entity.request.UserSaveRequest;
import unaldi.userservice.entity.request.UserUpdateRequest;
import unaldi.userservice.service.abstracts.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@SpringBootTest
public class MapperTest {
    @Test
    void should_convertSaveRequestToUser() {
        UserSaveRequest userSaveRequest = new UserSaveRequest(
                "eunaldi",
                "121221",
                "emree.unaldi@gmail.com",
                "Emre",
                "Ünaldı",
                "05078711189",
                LocalDate.of(2001, 1, 1),
                Gender.MALE
        );

        User user = UserMapper.INSTANCE.convertToSaveUser(userSaveRequest);

        assertEquals(userSaveRequest.username(), user.getUsername());
        assertEquals(userSaveRequest.password(), user.getPassword());
        assertEquals(userSaveRequest.email(), user.getEmail());
        assertEquals(userSaveRequest.firstName(), user.getFirstName());
        assertEquals(userSaveRequest.lastName(), user.getLastName());
        assertEquals(userSaveRequest.phoneNumber(), user.getPhoneNumber());
        assertEquals(userSaveRequest.birthDate(), user.getBirthDate());
        assertEquals(userSaveRequest.gender(), user.getGender());
    }

    @Test
    void should_convertUpdateRequestToUser() {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest(
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

        User user = UserMapper.INSTANCE.convertToUpdateUser(userUpdateRequest);

        assertEquals(userUpdateRequest.username(), user.getUsername());
        assertEquals(userUpdateRequest.password(), user.getPassword());
        assertEquals(userUpdateRequest.email(), user.getEmail());
        assertEquals(userUpdateRequest.firstName(), user.getFirstName());
        assertEquals(userUpdateRequest.lastName(), user.getLastName());
        assertEquals(userUpdateRequest.phoneNumber(), user.getPhoneNumber());
        assertEquals(userUpdateRequest.birthDate(), user.getBirthDate());
        assertEquals(userUpdateRequest.gender(), user.getGender());
    }

    @Test
    void should_convertUserToUserDTO() {
        User user = new User(
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

        UserDTO userDTO = UserMapper.INSTANCE.convertToUserDTO(user);

        assertEquals(userDTO.username(), user.getUsername());
        assertEquals(userDTO.password(), user.getPassword());
        assertEquals(userDTO.email(), user.getEmail());
        assertEquals(userDTO.firstName(), user.getFirstName());
        assertEquals(userDTO.lastName(), user.getLastName());
        assertEquals(userDTO.phoneNumber(), user.getPhoneNumber());
        assertEquals(userDTO.birthDate(), user.getBirthDate());
        assertEquals(userDTO.gender(), user.getGender());
    }

    @Test
    void should_convertListOfUsersToListOfUserDTOs() {
        List<User> userList = new ArrayList<>(Arrays.asList(
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
        ));

        List<UserDTO> userDTOList = UserMapper.INSTANCE.convertUserDTOs(userList);

        assertEquals(userList.get(0).getId(), userDTOList.get(0).id());
        assertEquals(userList.get(1).getId(), userDTOList.get(1).id());
    }
}
