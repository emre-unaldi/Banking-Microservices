package unaldi.bankservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Entity
@Table(name = "banks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "bank_name", nullable = false, length = 100)
    private String bankName;

    @NotBlank
    @Column(name = "bank_code", nullable = false, length = 20)
    private String bankCode;

    @NotBlank
    @Column(name = "branch_name", nullable = false, length = 100)
    private String branchName;

    @NotBlank
    @Column(name = "branch_code", nullable = false, length = 20)
    private String branchCode;

    @NotBlank
    @Column(name = "account_number", nullable = false, length = 20)
    private String accountNumber;

    @NotBlank
    @Column(name = "address", nullable = false, length = 250)
    private String address;

    @NotBlank
    @Column(name = "email", nullable = false, length = 250)
    private String email;

    @NotBlank
    @Column(name = "phone_number", nullable = false, length = 250)
    private String phoneNumber;
}