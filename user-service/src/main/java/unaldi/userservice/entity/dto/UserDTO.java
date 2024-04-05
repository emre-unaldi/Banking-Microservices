package unaldi.userservice.entity.dto;

import unaldi.userservice.entity.enums.Gender;

import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public record UserDTO(
        Long id,
        String username,
        String password,
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        LocalDate birthDate,
        Gender gender
) {
}
