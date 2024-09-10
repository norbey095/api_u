package com.emazon.api_user.infraestructure.exceptionhandler;

import com.emazon.api_user.domain.exception.MinorInvalidException;
import com.emazon.api_user.domain.exception.PhoneNumberinvalidException;
import com.emazon.api_user.domain.exception.UserAlreadyExistsException;
import com.emazon.api_user.domain.exception.UserEmailInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerUserAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException
            (MethodArgumentNotValidException ex) {
        StringBuilder messageBuilder = new StringBuilder();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            messageBuilder.append(errorMessage);
        });

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                messageBuilder.toString().trim(),
                HttpStatus.BAD_REQUEST.toString()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException
            (HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                ExceptionResponseConstants.FIELD_INVALID.getMessage(),
                HttpStatus.BAD_REQUEST.toString()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                ExceptionResponseConstants.USER_ALREADY_EXISTS.getMessage(),
                HttpStatus.CONFLICT.toString()));
    }

    @ExceptionHandler(UserEmailInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleUserEmailInvalidException(UserEmailInvalidException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                ExceptionResponseConstants.USER_EMAIL_CORRECT.getMessage(),
                HttpStatus.CONFLICT.toString()));
    }

    @ExceptionHandler(PhoneNumberinvalidException.class)
    public ResponseEntity<ExceptionResponse> handlePhoneNumberInvalidException(PhoneNumberinvalidException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                ExceptionResponseConstants.USER_PHONE_CORRECT.getMessage(),
                HttpStatus.CONFLICT.toString()));
    }

    @ExceptionHandler(MinorInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleMinorInvalidException(MinorInvalidException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                ExceptionResponseConstants.MINOR_INVALID.getMessage(),
                HttpStatus.CONFLICT.toString()));
    }
}
