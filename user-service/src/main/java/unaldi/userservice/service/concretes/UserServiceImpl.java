package unaldi.userservice.service.concretes;

import unaldi.userservice.entity.User;
import unaldi.userservice.entity.dto.UserDTO;
import unaldi.userservice.entity.request.UserSaveRequest;
import unaldi.userservice.entity.request.UserUpdateRequest;
import unaldi.userservice.repository.UserRepository;
import unaldi.userservice.service.abstracts.UserService;
import unaldi.userservice.service.abstracts.mapper.UserMapper;
import unaldi.userservice.utils.constant.ExceptionMessages;
import unaldi.userservice.utils.constant.Messages;
import unaldi.userservice.utils.exception.customExceptions.UserNotFoundException;
import unaldi.userservice.utils.result.DataResult;
import unaldi.userservice.utils.result.Result;
import unaldi.userservice.utils.result.SuccessDataResult;
import unaldi.userservice.utils.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public DataResult<UserDTO> save(UserSaveRequest userSaveRequest) {
        User user = UserMapper.INSTANCE.convertToSaveUser(userSaveRequest);
        this.userRepository.save(user);

        return new SuccessDataResult<>(
                UserMapper.INSTANCE.convertToUserDTO(user),
                Messages.USER_CREATED
        );
    }

    @Override
    public DataResult<UserDTO> update(UserUpdateRequest userUpdateRequest) {
        if (!this.userRepository.existsById(userUpdateRequest.id())) {
            throw new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND);
        }

        User user = UserMapper.INSTANCE.convertToUpdateUser(userUpdateRequest);
        this.userRepository.save(user);

        return new SuccessDataResult<>(
                UserMapper.INSTANCE.convertToUserDTO(user),
                Messages.USER_UPDATED
        );
    }

    @Override
    public Result deleteById(Long userId) {
        User user = this.userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

        this.userRepository.deleteById(user.getId());

        return new SuccessResult(Messages.USER_DELETED);
    }

    @Override
    public DataResult<UserDTO> findById(Long userId) {
        UserDTO userDTO = this.userRepository
                .findById(userId)
                .map(UserMapper.INSTANCE::convertToUserDTO)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

        return new SuccessDataResult<>(userDTO, Messages.USER_FOUND);
    }

    @Override
    public DataResult<List<UserDTO>> findAll() {
        List<User> userList = this.userRepository.findAll();

        return new SuccessDataResult<>(
                UserMapper.INSTANCE.convertUserDTOs(userList),
                Messages.USERS_LISTED
        );
    }
}
