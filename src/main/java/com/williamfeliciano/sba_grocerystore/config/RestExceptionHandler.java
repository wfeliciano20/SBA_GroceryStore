package com.williamfeliciano.sba_grocerystore.config;


import com.williamfeliciano.sba_grocerystore.dtos.ErrorDto;
import com.williamfeliciano.sba_grocerystore.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorDto> handleException(AppException ex) {
        ErrorDto errorDto = ErrorDto.builder()
                .errorCode(ex.getErrorCode())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(ex.getErrorCode()).body(errorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getBindingResult().getAllErrors());
    }
}
