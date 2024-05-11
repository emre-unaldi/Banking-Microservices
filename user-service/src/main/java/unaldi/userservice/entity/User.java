package unaldi.userservice.entity;

import lombok.*;
import unaldi.userservice.entity.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @NotBlank
    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @NotBlank
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @NotBlank
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NotBlank
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Past
    @Column(name = "birth_date", nullable = false)
    @NotBlank
    private LocalDate birthDate;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;
}
