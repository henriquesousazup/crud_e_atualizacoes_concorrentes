package com.example.zupedustore.model;

import javax.persistence.*;

@Entity
public class QuantidadeDeLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private Aplicativo aplicativo;

    @Column(nullable = false)
    private int quantidade;

    @Version
    private int versao;

    @Deprecated
    /**
     * @deprecated de uso exclusívo do Hibernate
     */
    public QuantidadeDeLikes() {
    }

    public QuantidadeDeLikes(Aplicativo aplicativo) {
        this.aplicativo = aplicativo;
        this.quantidade = 0;
    }

    public void incrementarQuantidade() {
        this.quantidade++;
    }
}
