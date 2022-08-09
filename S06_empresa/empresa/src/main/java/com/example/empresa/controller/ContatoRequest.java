package com.example.empresa.controller;

import com.example.empresa.model.Contato;
import com.example.empresa.model.Departamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class ContatoRequest {

    @NotBlank
    @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}")
    private String telefone;

    @NotBlank
    private String nome;

    @NotNull
    @Past
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    public Contato toModel(Departamento departamento) {
        return new Contato(telefone, nome, dataCadastro, departamento);

    }

    public String getTelefone() {
        return telefone;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }
}
