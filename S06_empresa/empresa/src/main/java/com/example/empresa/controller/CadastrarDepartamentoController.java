package com.example.empresa.controller;

import com.example.empresa.model.Departamento;
import com.example.empresa.repository.DepartamentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/departamentos")
public class CadastrarDepartamentoController {

    private final DepartamentoRepository repository;

    public CadastrarDepartamentoController(DepartamentoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid DepartamentoRequest request, UriComponentsBuilder uriComponentsBuilder) {

        if (repository.existsBySigla(request.getSigla())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Departamento com sigla já existente no sistema");
        }

        Departamento novoDepartamento = request.toModel();
        repository.save(novoDepartamento);

        URI location = uriComponentsBuilder
                .path("/departamentos/{id}")
                .buildAndExpand(novoDepartamento.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
