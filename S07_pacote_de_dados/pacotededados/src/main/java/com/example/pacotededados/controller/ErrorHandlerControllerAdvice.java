package com.example.pacotededados.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandlerControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolation(ConstraintViolationException exception, WebRequest request) {
        String mensagem = "Erro em valor que deve ser único";

        if (exception.getConstraintName().contains("UK_HASH_DO_CPF")) {
            mensagem = "CPF já existente no sistema.";
        }

        Map<String, Object> body = Map.of(
                "status", 422,
                "status_name", "unprocessable entity",
                "timestamp", LocalDateTime.now(),
                "path", request.getDescription(false).replace("uri=", ""),
                "message:", Arrays.asList(mensagem)
        );

        return ResponseEntity.unprocessableEntity().body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValid(MethodArgumentNotValidException exception, WebRequest request) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<String> mensagens = fieldErrors.stream().map(this::getMensagemErro).toList();

        Map<String, Object> body = Map.of(
                "status", 400,
                "status_name", "bad request",
                "timestamp", LocalDateTime.now(),
                "path", request.getDescription(false).replace("Uri=", ""),
                "message:", mensagens
        );

        return ResponseEntity.badRequest().body(body);
    }

    private String getMensagemErro(FieldError fieldError) {
        return String.format("Campo %s - %s", fieldError.getField(), fieldError.getDefaultMessage());
    }
}
