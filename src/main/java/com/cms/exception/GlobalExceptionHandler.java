package com.cms.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleNotValidArgument(MethodArgumentNotValidException notValidException) {
        Map<String, String> errorsMap = new HashMap<>();
        notValidException.getBindingResult().getFieldErrors()
                .forEach(error -> errorsMap.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMap);

    }

    @ExceptionHandler(CustomExceptionHandler.class)
    public ResponseEntity<Map<String, String>> handleCustomException(CustomExceptionHandler customExceptions) {

        Map<String, String> response = new HashMap<>();
        response.put("Error", "Bad Request");
        response.put("Message", customExceptions.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException() {

        Map<String, String> response = new HashMap<>();
        response.put("Error", "Bad Request");
        response.put("Message", "You can not delete or duplicate data(constraint)");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDenied(AccessDeniedException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("Error", "Forbidden");
        response.put("Message", "You do not have permission to access this resource");

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthenticationFailure(AuthenticationException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("Error", "Unauthorized");
        response.put("Message", "Authentication is required or has failed check your username or password");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {

        Map<String, String> response = new HashMap<>();
        response.put("Error", "Internal Server Error(500)");
        response.put("Message", "I can handle global exceptions");
        log.error("Unhandled request exception", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
