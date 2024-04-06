package unaldi.creditcardservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unaldi.creditcardservice.entity.CreditCard;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
