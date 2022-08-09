package com.example.carros.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_CHASSI_CARRO", columnNames = "chassi"),
        @UniqueConstraint(name = "UK_PLACA_CARRO", columnNames = "placa")
})
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false, length = 7)
    private String placa;

    @Column(nullable = false, length = 17)
    private String chassi;

    @Deprecated
    /**
     * @deprecated Uso exclusivo do Hibernate
     */
    public Carro() {
    }

    public Carro(String marca, String modelo, Integer ano, String placa, String chassi) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.chassi = chassi;
    }

    public Long getId() {
        return id;
    }
}
