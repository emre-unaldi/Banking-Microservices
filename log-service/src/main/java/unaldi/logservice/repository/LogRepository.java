package unaldi.logservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import unaldi.logservice.model.Log;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public interface LogRepository extends MongoRepository<Log, String> {
}
