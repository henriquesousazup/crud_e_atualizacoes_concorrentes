package com.example.carros.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ErrorHandlerControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValid(MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<String> mensagens = fieldErrors.stream().map(this::getMensagemErro).toList();

        return ResponseEntity.badRequest().body(mensagens);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolation(ConstraintViolationException exception) {

        String mensagem = "Erro em atributo com valor único";

        if (exception.getConstraintName().contains("UK_CHASSI_CARRO")) {
            mensagem = "Chassi já cadastrado no sistema";
        }

        if (exception.getConstraintName().contains("UK_PLACA_CARRO")) {
            mensagem = "Placa já cadastrado no sistema";
        }

        return ResponseEntity.unprocessableEntity().body(Arrays.asList(mensagem));
    }

    private String getMensagemErro(FieldError fieldError) {
        return String.format("Campo %s - %s", fieldError.getField(), fieldError.getDefaultMessage());
    }
}
