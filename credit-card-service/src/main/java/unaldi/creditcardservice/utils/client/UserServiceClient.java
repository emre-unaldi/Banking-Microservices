package unaldi.creditcardservice.utils.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import unaldi.creditcardservice.utils.client.dto.RestResponse;
import unaldi.creditcardservice.utils.client.dto.UserResponse;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@FeignClient(name = "user-service", url = "http://${USER_SERVICE_HOST:localhost}:8081")
public interface UserServiceClient {
    @GetMapping("/api/v1/users/{userId}")
    ResponseEntity<RestResponse<UserResponse>> findById(@PathVariable Long userId);
}
