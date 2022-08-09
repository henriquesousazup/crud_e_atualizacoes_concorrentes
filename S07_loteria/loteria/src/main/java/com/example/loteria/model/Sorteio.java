package com.example.loteria.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Sorteio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String descricao;

    @Column(nullable = false)
    private LocalDate dataSorteio;

    @Deprecated
    public Sorteio() {
    }

    public Sorteio(String descricao, LocalDate dataSorteio) {
        this.descricao = descricao;
        this.dataSorteio = dataSorteio;
    }

    public Long getId() {
        return id;
    }
}
