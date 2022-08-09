package com.example.livraria.controller;

import com.example.livraria.model.Livro;
import com.example.livraria.repository.LivroRepository;
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
public class CadastrarLivroController {

    private final LivroRepository repository;

    public CadastrarLivroController(LivroRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @PostMapping("/livros")
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid LivroRequest request, UriComponentsBuilder uriComponentsBuilder) {

        if (repository.existsByIsbn(request.getIsbn())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Livro com ISBN já cadastrado");
        }

        Livro livro = request.toModel();
        repository.save(livro);

        URI location = uriComponentsBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
