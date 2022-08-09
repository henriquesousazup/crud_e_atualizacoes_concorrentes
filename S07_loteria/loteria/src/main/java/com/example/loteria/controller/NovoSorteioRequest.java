package com.example.loteria.controller;

import com.example.loteria.model.Sorteio;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class NovoSorteioRequest {

    @NotBlank
    @Length(max = 150)
    private String descricao;

    @Future
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataSorteio;

    public Sorteio toModel() {
        return new Sorteio(descricao, dataSorteio);
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataSorteio() {
        return dataSorteio;
    }
}
