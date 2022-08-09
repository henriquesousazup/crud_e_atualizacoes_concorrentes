package com.example.reclamacoes.controller;

import com.example.reclamacoes.model.Reclamacao;
import com.example.reclamacoes.util.CelularUtil;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ReclamacaoRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}")
    private String celular;

    @NotBlank
    @Length(max = 4000)
    private String reclamacao;

    public Reclamacao toModel() {
        return new Reclamacao(nome, email, celular, reclamacao);
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCelular() {
        return celular;
    }

    public String getHashDoCelular() {
        return CelularUtil.hash(celular);
    }

    public String getReclamacao() {
        return reclamacao;
    }
}
