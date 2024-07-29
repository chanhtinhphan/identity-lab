package com.springlab.identity_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(999, "Uncategorized exception"),
    INVALID_MESSAGE_KEY(1001, "Invalid message key in source code"),
    USER_EXISTED(1002, "User existed"),
    INVALID_USERNAME(1003, "Username must be at least 3 characters"),
    INVALID_PASSWORD(1004, "Password must be at least 6 characters"),
    USER_NOT_EXISTED(1005, "User not existed"),
    UNAUTHENTICATED(1006, "Unauthenticated");
    private int code;
    private String message;
}
