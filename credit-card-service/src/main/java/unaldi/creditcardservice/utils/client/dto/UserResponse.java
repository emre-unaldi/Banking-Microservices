package unaldi.creditcardservice.utils.client.dto;

import unaldi.creditcardservice.utils.client.enums.Gender;

import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public record UserResponse(
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
