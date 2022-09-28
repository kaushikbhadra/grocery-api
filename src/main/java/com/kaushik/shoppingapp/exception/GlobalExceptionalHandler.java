package com.kaushik.shoppingapp.exception;

import com.kaushik.shoppingapp.model.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionalHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseModel> responseNotFoundException(ResourceNotFoundException exception){
       String message = exception.getMessage();
       ResponseModel responseModel = new ResponseModel(message, false);
       return new ResponseEntity<>(responseModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String,String> response = new HashMap<>();
        exception.getBindingResult().getAllErrors()
                .forEach(error -> {
                       String fieldName = ((FieldError)error).getField();
                       String message = error.getDefaultMessage();
                       response.put(fieldName, message);
                });
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseModel> exceptionHandler(Exception exception){
        String message = exception.getMessage();
        ResponseModel responseModel = new ResponseModel(message, false);
        return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
    }
}
