package unaldi.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unaldi.accountservice.entity.Account;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
