package com.example.carros.controller;

import com.example.carros.model.Carro;
import com.example.carros.repository.CarroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class CadastrarCarroController {

    private final CarroRepository repository;

    public CadastrarCarroController(CarroRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @PostMapping("/carros")
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid CarroRequest request, UriComponentsBuilder uriComponentsBuilder) {

        if (repository.existsByPlaca(request.getPlaca().toUpperCase()) || repository.existsByChassi(request.getChassi().toUpperCase())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Carro com placa/chassi já cadastrados");
        }

        Carro carro = request.toModel();
        repository.save(carro);

        URI location = uriComponentsBuilder.path("/carros/{id}").buildAndExpand(carro.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
