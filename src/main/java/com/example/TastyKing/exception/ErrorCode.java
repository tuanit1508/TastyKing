package com.example.TastyKing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum ErrorCode {
    EMAIL_EXISTED(1001, "Email has existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1002, "Username must at least 5 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003, "Password must at least 8 characters", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_EXISTED(1004, "Email has not existed", HttpStatus.NOT_FOUND),
    LOGIN_FAILED(1005, "Login failed. Please check your email or password", HttpStatus.FORBIDDEN),
    PHONE_INVALID(1006, "Number phone format invalid.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(1007, "Access Denied", HttpStatus.FORBIDDEN),
    UNAUTHENTICATED(1008,"Unauthenticated", HttpStatus.UNAUTHORIZED),

    CATEGORY_NOT_EXIST(1009,"Category not exist" ,HttpStatus.BAD_REQUEST ),
    CATEGORY_EXISTED(1010,"Category has existed" ,HttpStatus.BAD_REQUEST ),
    FOOD_NOT_EXIST(1011,"Food not exist",HttpStatus.BAD_REQUEST),
    PRICE_INVALID(1012, "Price must be greater than 0", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_MATCHER(1013,"Please enter correct old password", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_MATCH(1013,"Do not match. Please enter correct password", HttpStatus.BAD_REQUEST),
    OTP_INVALID(1014,"OTP invalid. Try again" ,HttpStatus.BAD_REQUEST );
    private int code;
    private String message;
    private HttpStatusCode statusCode;
    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
