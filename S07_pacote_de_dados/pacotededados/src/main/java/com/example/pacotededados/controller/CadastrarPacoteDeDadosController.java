package com.example.pacotededados.controller;

import com.example.pacotededados.model.PacoteDeDados;
import com.example.pacotededados.repository.PacoteDeDadosRepository;
import com.example.pacotededados.util.CpfUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class CadastrarPacoteDeDadosController {

    private final PacoteDeDadosRepository repository;

    public CadastrarPacoteDeDadosController(PacoteDeDadosRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/pacotes-de-dados")
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid PacoteDeDadosRequest request, UriComponentsBuilder uriComponentsBuilder) {

        String hashDoCpf = CpfUtils.hash(request.getCpf());

        if (repository.existsByHashDoCpf(hashDoCpf)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "CPF já existente no sistema.");
        }

        PacoteDeDados novoPacoteDeDados = request.toModel();
        repository.save(novoPacoteDeDados);

        URI location = uriComponentsBuilder
                .path("/pacotes-de-dados/{id}")
                .buildAndExpand(novoPacoteDeDados.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
