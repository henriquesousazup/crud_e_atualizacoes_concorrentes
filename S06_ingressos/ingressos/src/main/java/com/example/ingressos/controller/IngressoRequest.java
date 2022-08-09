package com.example.ingressos.controller;

import com.example.ingressos.model.Ingresso;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class IngressoRequest {

    @NotBlank
    private String nome;

    @CPF
    @NotBlank
    private String cpf;

    @NotNull
    private Integer numero;

    @Future
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEvento;

    public Ingresso toModel() {
        return new Ingresso(nome, cpf, numero, dataEvento);
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Integer getNumero() {
        return numero;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }
}
