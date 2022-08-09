package com.example.ingressos.controller;

import com.example.ingressos.model.Ingresso;
import com.example.ingressos.repository.IngressoRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class CadastrarIngressoController {

    private final IngressoRepository repository;

    public CadastrarIngressoController(IngressoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/ingressos")
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid IngressoRequest request, UriComponentsBuilder uriComponentsBuilder) {

        if (repository.existsByNumeroAndDataEvento(request.getNumero(), request.getDataEvento())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Ingresso já existente no sistema");
        }

        Ingresso novoIngresso = request.toModel();
        repository.save(novoIngresso);

        URI location = uriComponentsBuilder
                .path("/ingressos/{id}")
                .buildAndExpand(novoIngresso.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleUniqueConstraintErrors(ConstraintViolationException e, WebRequest request) {

        Map<String, Object> body = Map.of(
                "status", 422,
                "error", "Unprocessable Entity",
                "path", request.getDescription(false).replace("uri=", ""),
                "timestamp", LocalDateTime.now(),
                "message", "Ingresso já existente no sistema"
        );

        return ResponseEntity
                .unprocessableEntity().body(body);
    }
}

