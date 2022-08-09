package com.example.empresa.model;

import javax.persistence.*;
import java.time.LocalDate;

@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "UK_TELEFONE_DEPARTAMENTO_CONTATO",
                columnNames = {"telefone", "departamento_id"}
        )
})
@Entity
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataCadastro;

    @ManyToOne(optional = false)
    private Departamento departamento;

    @Deprecated
    public Contato() {
    }

    public Contato(String telefone, String nome, LocalDate dataCadastro, Departamento departamento) {
        this.telefone = telefone;
        this.nome = nome;
        this.dataCadastro = dataCadastro;
        this.departamento = departamento;
    }

    public Long getId() {
        return id;
    }
}
