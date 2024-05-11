package unaldi.userservice.entity.request;

import lombok.Builder;
import unaldi.userservice.entity.enums.Gender;

import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Builder
public record UserUpdateRequest(
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
