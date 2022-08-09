package com.example.ingressos.model;

import javax.persistence.*;
import java.time.LocalDate;

@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "UK_numero_data_evento",
                columnNames = {"numero", "dataEvento"}
        )
})
@Entity
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private LocalDate dataEvento;

    @Deprecated
    /**
     * @deprecated uso exclusivo do Hibernate
     */
    public Ingresso() {
    }

    public Ingresso(String nome, String cpf, Integer numero, LocalDate dataEvento) {
        this.nome = nome;
        this.cpf = cpf;
        this.numero = numero;
        this.dataEvento = dataEvento;
    }

    public Long getId() {
        return id;
    }
}
