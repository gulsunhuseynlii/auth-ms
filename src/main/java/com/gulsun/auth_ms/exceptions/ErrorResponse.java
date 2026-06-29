package com.gulsun.auth_ms.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
}
