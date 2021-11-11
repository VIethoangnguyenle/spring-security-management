package com.hoang.springsecuritylearn.exception;

import com.hoang.springsecuritylearn.core.dto.RestError;
import com.hoang.springsecuritylearn.core.dto.RestResponse;

public abstract class RestException extends RuntimeException{
    private final RestError restError;

    public RestException(RestError restError) {
        this.restError = restError;
    }

    public abstract RestResponse<RestError> getResponse();

    public RestError getRestError() {
        return restError;
    }
}
