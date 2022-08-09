package com.example.livraria.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandlerControllerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValid(MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<String> mensagens = fieldErrors.stream().map(this::getMensagemErro).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(mensagens);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(ConstraintViolationException exception) {

        String mensagem = "Erro em atributo que o valor deve ser único";

        if (exception.getConstraintName().contains("UK_LIVRO_ISBN")) {
            mensagem = "Livro com ISBN já cadastrado no sistema";
        }

        return ResponseEntity.unprocessableEntity().body(Arrays.asList(mensagem));
    }

    private String getMensagemErro(FieldError fieldError) {
        return String.format("Campo %s - %s", fieldError.getField(), fieldError.getDefaultMessage());
    }
}
