package com.example.produtos.controller;

import com.example.produtos.model.Produto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @Length(max = 6)
    private String codigo;

    @NotNull
    private BigDecimal preco;

    public ProdutoRequest() {
    }

    public ProdutoRequest(String nome, String codigo, BigDecimal preco) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
    }

    public Produto toModel() {
        return new Produto(nome, codigo, preco);
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
