package org.banking.pet.exception;

import org.banking.pet.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageService messageService;

    public GlobalExceptionHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiError> handleApiException(ApiException ex, Locale locale) {
        String message = messageService.getMessage(ex.getErrorCode(), locale);
        ApiError error = new ApiError(ex.getErrorCode().name(), message);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex, Locale locale) {
        String message = messageService.getMessage(ErrorCode.GENERIC, locale);
        ApiError error = new ApiError(ErrorCode.GENERIC.name(), message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
