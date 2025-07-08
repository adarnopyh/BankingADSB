package org.banking.pet.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiError {
    private String code;
    private String message;
    private LocalDateTime timestamp;

    public ApiError(String code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

}
