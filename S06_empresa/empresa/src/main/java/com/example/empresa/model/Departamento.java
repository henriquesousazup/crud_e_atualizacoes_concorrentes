package com.example.empresa.model;

import javax.persistence.*;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_SIGLA_DEPARTAMENTO", columnNames = "sigla")
})
@Entity
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 3)
    private String sigla;

    @Deprecated
    /**
     * @deprecated uso exclusivo do Hibernate
     */
    public Departamento() {
    }

    public Departamento(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla.toUpperCase();
    }

    public Long getId() {
        return id;
    }
}
