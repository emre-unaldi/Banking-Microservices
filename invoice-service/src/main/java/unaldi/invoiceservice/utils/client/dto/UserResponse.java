package unaldi.invoiceservice.utils.client.dto;

import lombok.Builder;
import unaldi.invoiceservice.utils.client.enums.Gender;

import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Builder
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
