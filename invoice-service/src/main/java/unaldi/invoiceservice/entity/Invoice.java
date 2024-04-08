package unaldi.invoiceservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unaldi.invoiceservice.entity.enums.PaymentStatus;

import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Entity
@Table(name = "invoices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "invoice_number", nullable = false, length = 20)
    private String invoiceNumber;

    @NotBlank
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotBlank
    @Column(name = "amount", nullable = false)
    private Double amount;

    @Past
    @Column(name = "invoice_date", nullable = false)
    @NotBlank
    private LocalDate invoiceDate;

    @Past
    @Column(name = "due_date", nullable = false)
    @NotBlank
    private LocalDate dueDate;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;
}