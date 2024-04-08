package unaldi.invoiceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unaldi.invoiceservice.entity.Invoice;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
