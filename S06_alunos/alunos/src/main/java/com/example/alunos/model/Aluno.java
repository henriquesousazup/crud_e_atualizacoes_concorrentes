package com.example.alunos.model;

import javax.persistence.*;
import java.time.LocalDate;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_NUMERO_E_DATA_MATRICULA", columnNames = {
            "numeroDeMatricula", "dataDeMatricula"
        })
})
@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false, length = 6)
    private String numeroDeMatricula;

    @Column(nullable = false)
    private LocalDate dataDeMatricula;

    @Deprecated
    /**
     * @deprecated uso exclusivo do Hibernate
     */
    public Aluno() {
    }

    public Aluno(String nome, String cpf, String numeroDeMatricula, LocalDate dataDeMatricula) {
        this.nome = nome;
        this.cpf = cpf;
        this.numeroDeMatricula = numeroDeMatricula;
        this.dataDeMatricula = dataDeMatricula;
    }

    public Long getId() {
        return id;
    }
}
