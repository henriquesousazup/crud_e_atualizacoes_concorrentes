package com.example.zupedustore.controller;

import com.example.zupedustore.model.Aplicativo;
import com.example.zupedustore.repository.AplicativoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LikeAplicativoController {

    private final AplicativoRepository repository;

    public LikeAplicativoController(AplicativoRepository repository) {
        this.repository = repository;
    }

    @PatchMapping("/aplicativos/{id}/likes")
    public ResponseEntity<Void> like(@PathVariable Long id) {

        Aplicativo aplicativo = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aplicativo não encontrado"));

        aplicativo.incrementarLike();
        repository.save(aplicativo);

        return ResponseEntity.noContent().build();
    }

}
