package com.example.alunos.controller;

import com.example.alunos.model.Aluno;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class AlunoRequest {

    @NotBlank
    private String nome;

    @CPF
    @NotBlank
    private String cpf;

    @NotBlank
    @Length(max = 6)
    private String numeroDeMatricula;

    @PastOrPresent
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeMatricula;

    public AlunoRequest() {
    }

    public AlunoRequest(String nome, String cpf, String numeroDeMatricula, LocalDate dataDeMatricula) {
        this.nome = nome;
        this.cpf = cpf;
        this.numeroDeMatricula = numeroDeMatricula;
        this.dataDeMatricula = dataDeMatricula;
    }

    public Aluno toModel() {
        return new Aluno(nome, cpf, numeroDeMatricula, dataDeMatricula);
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNumeroDeMatricula() {
        return numeroDeMatricula;
    }

    public LocalDate getDataDeMatricula() {
        return dataDeMatricula;
    }
}
