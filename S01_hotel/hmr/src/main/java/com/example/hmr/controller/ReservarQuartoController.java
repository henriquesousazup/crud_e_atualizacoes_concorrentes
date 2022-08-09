package com.example.hmr.controller;

import com.example.hmr.model.Quarto;
import com.example.hmr.model.Reserva;
import com.example.hmr.repository.QuartoRepository;
import com.example.hmr.repository.ReservaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/quartos/{quartoId}/reservas")
public class ReservarQuartoController {

    private final QuartoRepository quartoRepository;
    private final ReservaRepository reservaRepository;

    public ReservarQuartoController(QuartoRepository quartoRepository, ReservaRepository reservaRepository) {
        this.quartoRepository = quartoRepository;
        this.reservaRepository = reservaRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> reservar(@RequestBody @Valid ReservaRequest request, @PathVariable Long quartoId, UriComponentsBuilder uriComponentsBuilder) {

        Quarto quarto = quartoRepository.findById(quartoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quarto não cadastrado."));

        Reserva novaReserva = request.toModel(quarto);
        quarto.ativarReserva();

        this.reservaRepository.save(novaReserva);

        URI location = uriComponentsBuilder
                .path("/quartos/{quartoId}/reservas/{id}")
                .buildAndExpand(quarto.getId(), novaReserva.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
