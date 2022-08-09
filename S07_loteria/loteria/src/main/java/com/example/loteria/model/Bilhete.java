package com.example.loteria.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

import static com.example.loteria.util.StringUtils.anonymize;
import static com.example.loteria.util.StringUtils.hash;

@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "UK_CELULAR_NUMERO_DA_SORTE_SORTEIO_BILHETE",
                columnNames = {"sorteio_id", "hashDoCelular", "numeroDaSorte"}
        )
})
@Entity
public class Bilhete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 64)
    private String celular;

    @Column(nullable = false)
    private String hashDoCelular;

    @ManyToOne(optional = false)
    private Sorteio sorteio;

    @Column(nullable = false)
    private Integer numeroDaSorte;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate dataRegistro;

    @Deprecated
    public Bilhete() {
    }

    public Bilhete(String nome, String celular, Integer numeroDaSorte, Sorteio sorteio) {
        this.nome = nome;
        this.celular = anonymize(celular, 0, 4, "*");
        this.hashDoCelular = hash(celular);
        this.sorteio = sorteio;
        this.numeroDaSorte = numeroDaSorte;
    }

    public Long getId() {
        return id;
    }
}
