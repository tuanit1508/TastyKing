package com.example.TastyKing.Exception;

public enum ErrorCode {
    EMAIL_EXISTED(1001, "Email has existed"),
    USERNAME_INVALID(1002, "Username must at least 5 characters"),
    PASSWORD_INVALID(1003, "Password must at least 8 characters"),
    EMAIL_NOT_EXISTED(1004, "Email has not existed")
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
