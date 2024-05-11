package unaldi.userservice.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import unaldi.userservice.entity.User;
import unaldi.userservice.entity.dto.UserDTO;
import unaldi.userservice.entity.request.UserSaveRequest;
import unaldi.userservice.entity.request.UserUpdateRequest;
import unaldi.userservice.repository.UserRepository;
import unaldi.userservice.service.concretes.UserServiceImpl;
import unaldi.userservice.utils.FailTestMessages;
import unaldi.userservice.utils.ObjectFactory;
import unaldi.userservice.utils.exception.customExceptions.UserNotFoundException;
import unaldi.userservice.utils.rabbitMQ.producer.LogProducer;
import unaldi.userservice.utils.rabbitMQ.request.LogRequest;
import unaldi.userservice.utils.result.DataResult;
import unaldi.userservice.utils.result.Result;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private static User user;
    private static List<User> userList;
    private static UserSaveRequest userSaveRequest;
    private static UserUpdateRequest userUpdateRequest;
    private final Long nonExistentUserId = -1L;

    @Mock
    private UserRepository userRepository;
    @Mock
    private LogProducer logProducer;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeAll
    static void setUp() {
        user = ObjectFactory.getInstance().getUser();
        userList = ObjectFactory.getInstance().getUserList();
        userSaveRequest = ObjectFactory.getInstance().getUserSaveRequest();
        userUpdateRequest = ObjectFactory.getInstance().getUserUpdateRequest();
    }

    @Test
    void givenUserSaveRequest_whenSave_thenUserShouldBeSaved() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        DataResult<UserDTO> result = userService.save(userSaveRequest);
        assertTrue(result.getSuccess(), FailTestMessages.USER_SAVE);

        verify(userRepository, times(1)).save(any(User.class));
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenUserUpdateRequest_whenUpdate_thenUserShouldBeUpdated() {
        when(userRepository.existsById(userUpdateRequest.id())).thenReturn(true);
        when(userRepository.save(any(User.class))).thenReturn(user);

        DataResult<UserDTO> result = userService.update(userUpdateRequest);
        assertTrue(result.getSuccess(), FailTestMessages.USER_UPDATE);

        verify(userRepository, times(1)).existsById(anyLong());
        verify(userRepository, times(1)).save(any(User.class));
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenUserId_whenDeleteById_thenUserShouldBeDeleted() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        Result result = userService.deleteById(user.getId());
        assertTrue(result.getSuccess(), FailTestMessages.USER_DELETE);

        verify(userRepository, times(1)).deleteById(user.getId());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenUserId_whenFindById_thenUserShouldBeFound() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        DataResult<UserDTO> result = userService.findById(user.getId());
        assertTrue(result.getSuccess(), FailTestMessages.USER_FIND);

        verify(userRepository, times(1)).findById(user.getId());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenUserList_whenFindAll_thenAllUsersShouldBeReturned() {
        when(userRepository.findAll()).thenReturn(userList);

        DataResult<List<UserDTO>> result = userService.findAll();
        assertTrue(result.getSuccess(), FailTestMessages.USERS_FIND);

        verify(userRepository, times(1)).findAll();
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenNonExistentUserUpdateRequest_whenUpdate_thenUserNotFoundExceptionShouldBeThrown() {
        when(userRepository.existsById(userUpdateRequest.id())).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> {
            userService.update(userUpdateRequest);
        }, FailTestMessages.USER_UPDATE_EXCEPTION);

        verify(userRepository, times(1)).existsById(anyLong());
        verify(userRepository, never()).save(any(User.class));
        verify(logProducer, never()).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenNonExistentUserId_whenDeleteById_thenUserNotFoundExceptionShouldBeThrown() {
        when(userRepository.findById(nonExistentUserId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.deleteById(nonExistentUserId);
        }, FailTestMessages.USER_DELETE_EXCEPTION);

        verify(userRepository, times(1)).findById(nonExistentUserId);
        verify(userRepository, never()).deleteById(anyLong());
        verify(logProducer, never()).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenNonExistentUserId_whenFindById_thenUserNotFoundExceptionShouldBeThrown() {
        when(userRepository.findById(nonExistentUserId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.findById(nonExistentUserId);
        }, FailTestMessages.USER_FIND_EXCEPTION);

        verify(userRepository, times(1)).findById(nonExistentUserId);
        verify(logProducer, never()).sendToLog(any(LogRequest.class));
    }
}
