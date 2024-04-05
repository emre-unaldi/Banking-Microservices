package unaldi.userservice.service.abstracts;

import unaldi.userservice.entity.dto.UserDTO;
import unaldi.userservice.entity.request.UserSaveRequest;
import unaldi.userservice.entity.request.UserUpdateRequest;
import unaldi.userservice.utils.result.DataResult;
import unaldi.userservice.utils.result.Result;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public interface UserService {
    DataResult<UserDTO> save(UserSaveRequest userSaveRequest);
    DataResult<UserDTO> update(UserUpdateRequest userUpdateRequest);
    Result deleteById(Long userId);
    DataResult<UserDTO> findById(Long userId);
    DataResult<List<UserDTO>> findAll();
}
