package com.example.TastyKing.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized error."),
    EMAIL_EXISTED(1001, "Email has existed"),
    USERNAME_INVALID(1002, "Username must at least 5 characters"),
    PASSWORD_INVALID(1003, "Password must at least 8 characters"),
    EMAIL_NOT_EXISTED(1004, "Email has not existed"),
    LOGIN_FAILED(1005, "Login failed. Please check your email or password"),
    USER_NOT_EXISTED(1006,"User not existed."),
    USER_EXISTED(1007,"User existed."),
    UNAUTHENTICATED(1008,"Unauthenticated.")
    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
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
}
