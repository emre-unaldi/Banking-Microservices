package unaldi.userservice.controller;

import unaldi.userservice.entity.dto.UserDTO;
import unaldi.userservice.entity.request.UserSaveRequest;
import unaldi.userservice.entity.request.UserUpdateRequest;
import unaldi.userservice.service.abstracts.UserService;
import unaldi.userservice.utils.result.DataResult;
import unaldi.userservice.utils.result.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<DataResult<UserDTO>> save(@Valid @RequestBody UserSaveRequest userSaveRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.userService.save(userSaveRequest));
    }

    @PutMapping
    public ResponseEntity<DataResult<UserDTO>> update(@RequestBody UserUpdateRequest userUpdateRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.userService.update(userUpdateRequest));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Result> delete(@PathVariable Long userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.userService.deleteById(userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<DataResult<UserDTO>> findById(@PathVariable Long userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.userService.findById(userId));
    }

    @GetMapping
    public ResponseEntity<DataResult<List<UserDTO>>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.userService.findAll());
    }
}
