package unaldi.creditcardservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Entity
@Table(name = "credit_cards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "card_number", nullable = false, length = 20)
    private String cardNumber;

    @NotBlank
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Past
    @Column(name = "expiration_date", nullable = false)
    @NotBlank
    private LocalDate expirationDate;

    @NotBlank
    @Column(name = "cvv", nullable = false, length = 4)
    private String cvv;

    @NotBlank
    @Column(name = "credit_limit", nullable = false)
    private Double creditLimit;

    @NotBlank
    @Column(name = "debt_amount", nullable = false)
    private Double debtAmount;

    @NotBlank
    @Column(name = "bank_id", nullable = false)
    private Long bankId;
}