package com.example.loteria.controller;

import com.example.loteria.model.Bilhete;
import com.example.loteria.repository.BilheteRepository;
import com.example.loteria.repository.SorteioRepository;
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
public class CadastrarBilheteController {

    private final BilheteRepository bilheteRepository;
    private final SorteioRepository sorteioRepository;

    public CadastrarBilheteController(BilheteRepository bilheteRepository, SorteioRepository sorteioRepository) {
        this.bilheteRepository = bilheteRepository;
        this.sorteioRepository = sorteioRepository;
    }

    @Transactional
    @PostMapping("/bilhetes")
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid NovoBilheteRequest request, UriComponentsBuilder uriComponentsBuilder) {

        if (bilheteRepository.existsByHashDoCelularAndNumeroDaSorteAndSorteioId(request.getHashDoCelular(), request.getNumeroDaSorte(), request.getSorteioId())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Usuário já possui numero da sorte cadastrado nesse sorteio.");
        }

        Bilhete novoBilhete = request.toModel(sorteioRepository);
        bilheteRepository.save(novoBilhete);

        URI location = uriComponentsBuilder
                .path("/bilhetes/{id}")
                .buildAndExpand(novoBilhete.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
