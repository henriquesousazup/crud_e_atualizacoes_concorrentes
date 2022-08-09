package com.example.empresa.controller;

import com.example.empresa.model.Contato;
import com.example.empresa.model.Departamento;
import com.example.empresa.repository.ContatoRepository;
import com.example.empresa.repository.DepartamentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/departamentos/{departamentoId}")
public class CadastrarContatoController {

    private final ContatoRepository contatoRepository;
    private final DepartamentoRepository departamentoRepository;

    public CadastrarContatoController(ContatoRepository contatoRepository, DepartamentoRepository departamentoRepository) {
        this.contatoRepository = contatoRepository;
        this.departamentoRepository = departamentoRepository;
    }

    @PostMapping("/contatos")
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid ContatoRequest request,
                                          @PathVariable Long departamentoId, UriComponentsBuilder uriComponentsBuilder) {

        Departamento departamento = departamentoRepository.findById(departamentoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Departamento não encontrado no sistema."));

        if(contatoRepository.existsByTelefoneAndDepartamentoId(request.getTelefone(), departamentoId)){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Já existe contato com esse telefone no departamento solicitado.");
        }

        Contato novoContato = request.toModel(departamento);
        contatoRepository.save(novoContato);

        URI location = uriComponentsBuilder
                .path("/departamentos/{departamentoId}/contatos/{id}")
                .buildAndExpand(departamento.getId(), novoContato.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
