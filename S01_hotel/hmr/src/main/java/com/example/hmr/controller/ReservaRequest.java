package com.example.hmr.controller;

import com.example.hmr.model.Quarto;
import com.example.hmr.model.Reserva;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ReservaRequest {

    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkIn;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkOut;

    public ReservaRequest() {
    }

    public ReservaRequest(LocalDate checkIn, LocalDate checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Reserva toModel(Quarto quartoReservado) {

        if (quartoReservado.getReservaAtiva()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Esse quarto já possui reserva ativa.");
        }

        return new Reserva(quartoReservado, checkIn, checkOut);
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }
}
