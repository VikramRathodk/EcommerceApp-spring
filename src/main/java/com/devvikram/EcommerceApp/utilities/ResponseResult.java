package com.devvikram.EcommerceApp.utilities;

import org.springframework.http.HttpStatus;

public sealed class ResponseResult<T> permits ResponseResult.Success, ResponseResult.Error {

    private final int status;
    private final T data;
    private final String message;

    public ResponseResult(HttpStatus status, T data, String message) {
        this.status = status.value();
        this.data = data;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static final class Success<T> extends ResponseResult<T> {
        public Success(T data, String message) {
            super(HttpStatus.OK, data, message);
        }
    }

    public static final class Error<T> extends ResponseResult<T> {
        public Error(HttpStatus status, String message) {
            super(status, null, message);
        }
    }
}
