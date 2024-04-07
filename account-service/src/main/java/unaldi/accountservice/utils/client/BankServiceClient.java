package unaldi.accountservice.utils.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import unaldi.accountservice.utils.client.dto.BankResponse;
import unaldi.accountservice.utils.client.dto.RestResponse;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@FeignClient(name = "bank-service", url = "http://localhost:8082")
public interface BankServiceClient {
    @GetMapping("/api/v1/banks/{bankId}")
    ResponseEntity<RestResponse<BankResponse>> findById(@PathVariable Long bankId);
}
