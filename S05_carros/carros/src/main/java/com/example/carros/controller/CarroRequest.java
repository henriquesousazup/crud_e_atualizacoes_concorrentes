package com.example.carros.controller;

import com.example.carros.model.Carro;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class CarroRequest {

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotNull
    @Min(2010)
    @Max(2099)
    private Integer ano;

    @NotNull
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    private String placa;

    @NotBlank
    @Length(min = 17, max = 17)
    private String chassi;

    public Carro toModel() {
        return new Carro(marca, modelo, ano, placa, chassi);
    }

    public CarroRequest() {
    }

    public CarroRequest(String marca, String modelo, Integer ano, String placa, String chassi) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.chassi = chassi;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public String getPlaca() {
        return placa.toUpperCase();
    }

    public String getChassi() {
        return chassi.toUpperCase();
    }
}
