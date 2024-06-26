package unaldi.invoiceservice.utils.client.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Getter
@Setter
@Builder
public class RestResponse<T> {
    private boolean success;
    private String message;
    private String responseDateTime;
    private T data;
}
