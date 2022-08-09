package com.example.produtos.controller;

import com.example.produtos.model.Produto;
import com.example.produtos.repository.ProdutoRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class CadastrarProdutoController {

    private final ProdutoRepository repository;

    public CadastrarProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @PostMapping("/produtos")
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid ProdutoRequest request, UriComponentsBuilder uriComponentsBuilder) {

        if (repository.existsByCodigo(request.getCodigo())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe produto com esse código.");
        }

        Produto produto = request.toModel();
        repository.save(produto);

        URI location = uriComponentsBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
