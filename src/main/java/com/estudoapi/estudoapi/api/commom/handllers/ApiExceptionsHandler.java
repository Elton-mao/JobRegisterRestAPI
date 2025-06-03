package com.estudoapi.estudoapi.api.commom.handllers;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.estudoapi.estudoapi.api.commom.dto.ErrorResponse;
import com.estudoapi.estudoapi.api.commom.dto.ValidationErrorResponse;
import com.estudoapi.estudoapi.core.exceptions.ModelNotFoundExceptions;

@RestControllerAdvice
public class ApiExceptionsHandler {
    @ExceptionHandler(ModelNotFoundExceptions.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(ModelNotFoundExceptions exception){
        return ErrorResponse.builder()
            .message(exception.getMessage())
            .build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handlerMethodArgumentException(MethodArgumentNotValidException exception){
        var errors =exception.getBindingResult().getFieldErrors()
        .stream()
        .collect(Collectors.groupingBy(
            fieldError -> fieldError.getField(),
            Collectors.mapping(
                fieldError -> fieldError.getDefaultMessage(), Collectors.toList())
        ));
        return ValidationErrorResponse.builder()
            .message("Erro de validação")
            .errors(errors)
            .build();
                 
    }

    
}
