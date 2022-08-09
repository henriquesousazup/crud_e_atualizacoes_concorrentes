package com.example.pacotededados.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

import static com.example.pacotededados.util.CpfUtils.*;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_HASH_DO_CPF", columnNames = "hashDoCpf")
})
@Entity
public class PacoteDeDados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false, length = 64)
    private String hashDoCpf;

    @Column(nullable = false)
    private String celular;

    @Column(nullable = false)
    private Integer gigas;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate dataCadastro;

    @Deprecated
    /**
     * @deprecated uso exclusivo do Hibernate
     */
    public PacoteDeDados() {
    }

    public PacoteDeDados(String nome, String cpf, String celular, Integer gigas) {
        this.nome = nome;
        this.cpf = anonymize(cpf);
        this.hashDoCpf = hash(cpf);
        this.celular = celular;
        this.gigas = gigas;
    }

    public Long getId() {
        return id;
    }
}
