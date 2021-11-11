package com.hoang.springsecuritylearn.exception;

import com.hoang.springsecuritylearn.core.dto.RestError;
import com.hoang.springsecuritylearn.core.dto.RestResponse;

public class RestNotFoundErrorException extends RestException{

    public RestNotFoundErrorException(RestError restError) {
        super(restError);
    }

    @Override
    public RestResponse<RestError> getResponse() {
        return new RestResponse<RestError>().notFound(getRestError());
    }
}
