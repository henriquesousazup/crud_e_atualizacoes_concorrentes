package com.example.reclamacoes.controller;

import com.example.reclamacoes.model.Reclamacao;
import com.example.reclamacoes.repository.ReclamacaoRepository;
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
public class CadastrarReclamacaoController {

    private final ReclamacaoRepository repository;

    public CadastrarReclamacaoController(ReclamacaoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/reclamacoes")
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid ReclamacaoRequest request, UriComponentsBuilder uriComponentsBuilder) {

        if (repository.existsByHashDoCelularAndReclamacao(request.getHashDoCelular(), request.getReclamacao())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Já existe essa reclamação vinculada ao número de celular");
        }

        Reclamacao novaReclamacao = request.toModel();
        repository.save(novaReclamacao);

        URI location = uriComponentsBuilder.path("/reclamacoes/{id}").buildAndExpand(novaReclamacao.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
