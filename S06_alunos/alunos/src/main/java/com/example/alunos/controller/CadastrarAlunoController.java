package com.example.alunos.controller;

import com.example.alunos.model.Aluno;
import com.example.alunos.repository.AlunoRepository;
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
public class CadastrarAlunoController {

    private final AlunoRepository repository;

    public CadastrarAlunoController(AlunoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @PostMapping("/alunos")
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid AlunoRequest request, UriComponentsBuilder uriComponentsBuilder) {

        if (repository.existsByNumeroDeMatriculaAndDataDeMatricula(request.getNumeroDeMatricula(), request.getDataDeMatricula())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Já existe um aluno com o numero de matricula cadastrado nesta data");
        }

        Aluno novoAluno = request.toModel();
        repository.save(novoAluno);

        URI location = uriComponentsBuilder.path("/alunos/{id}").buildAndExpand(novoAluno.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
