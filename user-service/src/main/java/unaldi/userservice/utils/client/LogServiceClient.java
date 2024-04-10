package unaldi.userservice.utils.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import unaldi.userservice.utils.client.dto.LogRequest;
import unaldi.userservice.utils.client.dto.LogResponse;
import unaldi.userservice.utils.client.dto.RestResponse;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@FeignClient(name = "log-service", url = "http://localhost:8086")
public interface LogServiceClient {
    @PostMapping("/api/v1/logs")
    ResponseEntity<RestResponse<LogResponse>> sendToLog(@RequestBody LogRequest logRequest);
}
