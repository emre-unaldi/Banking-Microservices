package unaldi.userservice.entity.request;

import lombok.Builder;
import unaldi.userservice.entity.enums.Gender;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Builder
public record UserSaveRequest(
        @NotNull
        String username,
        @NotNull
        String password,
        @NotNull
        String email,
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull
        String phoneNumber,
        @NotNull
        LocalDate birthDate,
        @NotNull
        Gender gender
) {
}
