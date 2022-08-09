package com.example.loteria.controller;

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
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandlerControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(ConstraintViolationException exception, WebRequest request) {

        String mensagem = "Erro em atributo que o valor deve ser único";

        if (exception.getConstraintName().contains("UK_CELULAR_NUMERO_DA_SORTE_SORTEIO_BILHETE")) {
            mensagem = "Usuário já possui numero da sorte cadastrado nesse sorteio.";
        }

        Map<String, Object> body = Map.of(
                "error", "Unprocessable Entity",
                "message", Arrays.asList(mensagem),
                "path", request.getDescription(false).replace("uri=", ""),
                "status", 422,
                "timestamp", LocalDateTime.now()
        );

        return ResponseEntity.unprocessableEntity().body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValid(MethodArgumentNotValidException exception, WebRequest request) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<String> mensagens = fieldErrors.stream().map(this::getMensagemErro).collect(Collectors.toList());

        Map<String, Object> body = Map.of(
                "error", "Bad Request",
                "message", mensagens,
                "path", request.getDescription(false).replace("uri=", ""),
                "status", 400,
                "timestamp", LocalDateTime.now()
        );

        return ResponseEntity.badRequest().body(body);
    }

    private String getMensagemErro(FieldError fieldError) {
        return String.format("Campo %s - %s", fieldError.getField(), fieldError.getDefaultMessage());
    }
}
