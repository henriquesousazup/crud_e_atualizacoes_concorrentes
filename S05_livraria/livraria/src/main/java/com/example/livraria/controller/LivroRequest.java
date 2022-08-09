package com.example.livraria.controller;

import com.example.livraria.model.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.ISBN;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class LivroRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    @ISBN
    private String isbn;

    @NotNull
    @Past
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDePublicacao;

    public LivroRequest() {
    }

    public LivroRequest(String titulo, String isbn, LocalDate dataDePublicacao) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.dataDePublicacao = dataDePublicacao;
    }

    public Livro toModel() {
        return new Livro(titulo, isbn, dataDePublicacao);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataDePublicacao() {
        return dataDePublicacao;
    }
}
