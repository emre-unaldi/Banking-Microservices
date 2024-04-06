package unaldi.bankservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unaldi.bankservice.entity.Bank;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
}
