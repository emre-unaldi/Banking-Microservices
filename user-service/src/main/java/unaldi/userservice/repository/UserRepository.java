package unaldi.userservice.repository;

import unaldi.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
