package com.example.zupedustore.controller;

import com.example.zupedustore.model.Aplicativo;
import com.example.zupedustore.repository.AplicativoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@RestController
public class DownloadAplicativoController {

    private final AplicativoRepository repository;

    public DownloadAplicativoController(AplicativoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @PatchMapping("/aplicativos/{id}/downloads")
    public ResponseEntity<DownloadAplicativoResponse> download(@PathVariable Long id) {

        Aplicativo aplicativo = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aplicativo não encontrado."));

        aplicativo.incrementarDownload();
        return ResponseEntity.ok(new DownloadAplicativoResponse(aplicativo));
    }
}
