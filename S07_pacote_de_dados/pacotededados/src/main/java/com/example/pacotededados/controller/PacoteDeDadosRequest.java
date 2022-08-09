package com.example.pacotededados.controller;

import com.example.pacotededados.model.PacoteDeDados;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;

public class PacoteDeDadosRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}")
    private String celular;

    @NotNull
    @Min(5)
    @Max(50)
    private Integer gigas;

    public PacoteDeDados toModel() {
        return new PacoteDeDados(nome, cpf, celular, gigas);
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCelular() {
        return celular;
    }

    public Integer getGigas() {
        return gigas;
    }
}
