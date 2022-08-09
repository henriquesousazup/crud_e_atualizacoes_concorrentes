package com.example.loteria.controller;

import com.example.loteria.model.Sorteio;
import com.example.loteria.repository.SorteioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class CadastrarSorteioController {

    private final SorteioRepository repository;

    public CadastrarSorteioController(SorteioRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/sorteios")
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody NovoSorteioRequest request, UriComponentsBuilder uriComponentsBuilder) {

        Sorteio novoSorteio = request.toModel();

        repository.save(novoSorteio);

        URI location = uriComponentsBuilder
                .path("/sorteios/{id}")
                .buildAndExpand(novoSorteio.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
