package com.example.empresa.controller;

import com.example.empresa.model.Departamento;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class DepartamentoRequest {

    @NotBlank
    @Length(max = 120)
    private String nome;

    @Pattern(regexp = "[A-Z]+")
    @Length(max = 3)
    private String sigla;

    public Departamento toModel() {
        return new Departamento(nome, sigla);
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla.toUpperCase();
    }
}
