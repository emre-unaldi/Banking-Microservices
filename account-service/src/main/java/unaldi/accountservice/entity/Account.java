package unaldi.accountservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import unaldi.accountservice.entity.enums.AccountStatus;
import unaldi.accountservice.entity.enums.AccountType;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "account_number", nullable = false, length = 20)
    private String accountNumber;

    @NotBlank
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotBlank
    @Column(name = "balance", nullable = false)
    private Double balance;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false)
    private AccountStatus accountStatus;

    @NotBlank
    @Column(name = "bank_id", nullable = false)
    private Long bankId;
}

