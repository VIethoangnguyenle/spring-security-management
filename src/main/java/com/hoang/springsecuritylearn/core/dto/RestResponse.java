package com.hoang.springsecuritylearn.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hoang.springsecuritylearn.core.utils.FnCommon;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestResponse<T> {
    private T data;
    private String message;
    private Integer code;
    private String time;

    public RestResponse() {
        this.time = FnCommon.todayStr(null);
    }

    public RestResponse<T> success(){
        this.code = 200;
        this.message = "success";
        return this;
    }

    public RestResponse<T> success(T data){
        this.data = data;
        this.code = 200;
        this.message = "success";
        return this;
    }

    public RestResponse<T> badRequest(){
        this.code = 400;
        this.message = "bad_request";
        return this;
    }

    public RestResponse<T> badRequest(T data){
        this.data = data;
        this.code = 400;
        this.message = "bad_request";
        return this;
    }

    public RestResponse<T> notAllow(){
        this.code = 401;
        this.message = "not_allow";
        return this;
    }

    public RestResponse<T> notAllow(T data){
        this.data = data;
        this.code = 401;
        this.message = "not_allow";
        return this;
    }

    public RestResponse<T> unauthorized(){
        this.code = 401;
        this.message = "unauthorized";
        return this;
    }

    public RestResponse<T> unauthorized(T data){
        this.data = data;
        this.code = 401;
        this.message = "unauthorized";
        return this;
    }

    public RestResponse<T> serverError(){
        this.code = 500;
        this.message = "server_error";
        return this;
    }

    public RestResponse<T> serverError(T data){
        this.data = data;
        this.code = 500;
        this.message = "server_error";
        return this;
    }

    public RestResponse<T> notFound(){
        this.code = 404;
        this.message = "not_found";
        return this;
    }

    public RestResponse<T> notFound(T data){
        this.data = data;
        this.code = 404;
        this.message = "not_found";
        return this;
    }
}
