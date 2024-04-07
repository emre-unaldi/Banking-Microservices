package unaldi.accountservice.utils.result;

import lombok.Getter;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Getter
public class DataResult<T> extends Result {
    private final T data;

    public DataResult(Boolean success, T data) {
        super(success);
        this.data = data;
    }

    public DataResult(Boolean success, String message, T data) {
        super(success, message);
        this.data = data;
    }
}
