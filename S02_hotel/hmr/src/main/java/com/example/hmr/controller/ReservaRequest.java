package com.example.hmr.controller;

import com.example.hmr.model.Quarto;
import com.example.hmr.model.Reserva;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ReservaRequest {

    @NotBlank
    private String reservadoPara;
    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkIn;

    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkOut;

    public ReservaRequest() {
    }

    public ReservaRequest(String reservadoPara, LocalDate checkIn, LocalDate checkOut) {
        this.reservadoPara = reservadoPara;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Reserva toModel(Quarto quartoReservado) {
        return new Reserva(reservadoPara, checkIn, checkOut);
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public String getReservadoPara() {
        return reservadoPara;
    }
}
