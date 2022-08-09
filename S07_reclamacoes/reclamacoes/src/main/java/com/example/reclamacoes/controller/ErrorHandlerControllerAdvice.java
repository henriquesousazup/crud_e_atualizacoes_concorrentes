package com.example.reclamacoes.controller;

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

        String mensagem = "Erro em atributo com valor que deve ser único.";

        if(exception.getConstraintName().contains("UK_TELEFONE_TEXTO_RECLAMACAO")){
            mensagem = "Já existe essa reclamação vinculada ao número de celular";
        }

        Map<String, Object> body = Map.of(
                "status", 422,
                "message", Arrays.asList(mensagem),
                "timestamp", LocalDateTime.now(),
                "path", request.getDescription(false).replace("uri=", ""),
                "error", "Unprocessable Entity"
        );

        return ResponseEntity.unprocessableEntity().body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValid(MethodArgumentNotValidException exception, WebRequest request) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<String> mensagens = fieldErrors.stream().map(this::getMensagemErro).toList();

        Map<String, Object> body = Map.of(
                "status", 400,
                "message", mensagens,
                "timestamp", LocalDateTime.now(),
                "path", request.getDescription(false).replace("uri=", ""),
                "error", "Bad Request"
        );

        return ResponseEntity.badRequest().body(body);
    }

    private String getMensagemErro(FieldError fieldError) {
        return String.format("Campo %s - %s", fieldError.getField(), fieldError.getDefaultMessage());
    }
}
