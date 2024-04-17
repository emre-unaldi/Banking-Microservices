package unaldi.userservice.service.concretes;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import unaldi.userservice.entity.User;
import unaldi.userservice.entity.dto.UserDTO;
import unaldi.userservice.entity.request.UserSaveRequest;
import unaldi.userservice.entity.request.UserUpdateRequest;
import unaldi.userservice.repository.UserRepository;
import unaldi.userservice.service.abstracts.UserService;
import unaldi.userservice.service.abstracts.mapper.UserMapper;
import unaldi.userservice.utils.constant.Generals;
import unaldi.userservice.utils.rabbitMQ.request.LogRequest;
import unaldi.userservice.utils.rabbitMQ.enums.LogType;
import unaldi.userservice.utils.rabbitMQ.enums.OperationType;
import unaldi.userservice.utils.rabbitMQ.producer.LogProducer;
import unaldi.userservice.utils.constant.ExceptionMessages;
import unaldi.userservice.utils.constant.Messages;
import unaldi.userservice.utils.exception.customExceptions.UserNotFoundException;
import unaldi.userservice.utils.result.DataResult;
import unaldi.userservice.utils.result.Result;
import unaldi.userservice.utils.result.SuccessDataResult;
import unaldi.userservice.utils.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private final LogProducer logProducer;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, LogProducer logProducer) {
        this.userRepository = userRepository;
        this.logProducer = logProducer;
    }

    @CacheEvict(value = Generals.USERS_CACHE, allEntries = true, condition = "#result.success != false")
    @Override
    public DataResult<UserDTO> save(UserSaveRequest userSaveRequest) {
        User user = UserMapper.INSTANCE.convertToSaveUser(userSaveRequest);
        this.userRepository.save(user);

        logProducer.sendToLog(prepareLogRequest(OperationType.POST,Messages.USER_CREATED));

        return new SuccessDataResult<>(
                UserMapper.INSTANCE.convertToUserDTO(user),
                Messages.USER_CREATED
        );
    }

    @CachePut(value = Generals.USER_CACHE, key = "#userUpdateRequest.id()", unless = "#result.success != true")
    @CacheEvict(value = Generals.USERS_CACHE, allEntries = true, condition = "#result.success != false")
    @Override
    public DataResult<UserDTO> update(UserUpdateRequest userUpdateRequest) {
        if (!this.userRepository.existsById(userUpdateRequest.id())) {
            throw new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND);
        }

        User user = UserMapper.INSTANCE.convertToUpdateUser(userUpdateRequest);
        this.userRepository.save(user);

        logProducer.sendToLog(prepareLogRequest(OperationType.PUT,Messages.USER_UPDATED));

        return new SuccessDataResult<>(
                UserMapper.INSTANCE.convertToUserDTO(user),
                Messages.USER_UPDATED
        );
    }

    @Caching(
            evict = {
                    @CacheEvict(value = Generals.USERS_CACHE, allEntries = true, condition = "#result.success != false"),
                    @CacheEvict(value = Generals.USER_CACHE, key = "#userId", condition = "#result.success != false")
            }
    )
    @Override
    public Result deleteById(Long userId) {
        User user = this.userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

        this.userRepository.deleteById(user.getId());

        logProducer.sendToLog(prepareLogRequest(OperationType.DELETE,Messages.USER_DELETED));

        return new SuccessResult(Messages.USER_DELETED);
    }

    @Cacheable(value = Generals.USER_CACHE, key = "#userId", unless = "#result.success != true")
    @Override
    public DataResult<UserDTO> findById(Long userId) {
        UserDTO userDTO = this.userRepository
                .findById(userId)
                .map(UserMapper.INSTANCE::convertToUserDTO)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

        logProducer.sendToLog(prepareLogRequest(OperationType.GET,Messages.USER_FOUND));

        return new SuccessDataResult<>(userDTO, Messages.USER_FOUND);
    }

    @Cacheable(value = Generals.USERS_CACHE, key = "'all'", unless = "#result.success != true")
    @Override
    public DataResult<List<UserDTO>> findAll() {
        List<User> userList = this.userRepository.findAll();

        logProducer.sendToLog(prepareLogRequest(OperationType.GET,Messages.USERS_LISTED));

        return new SuccessDataResult<>(
                UserMapper.INSTANCE.convertUserDTOs(userList),
                Messages.USERS_LISTED
        );
    }

    private LogRequest prepareLogRequest(
            OperationType operationType,
            String message
    )
    {
        return LogRequest
                .builder()
                .serviceName(Generals.APPLICATION_NAME)
                .operationType(operationType)
                .logType(LogType.INFO)
                .message(message)
                .timestamp(LocalDateTime.now())
                .exception(null)
                .build();
    }
}
