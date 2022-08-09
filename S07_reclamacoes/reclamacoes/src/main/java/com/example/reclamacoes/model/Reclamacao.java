package com.example.reclamacoes.model;

import com.example.reclamacoes.util.CelularUtil;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "UK_TELEFONE_TEXTO_RECLAMACAO",
                columnNames = {"hashDoCelular", "reclamacao"}
        )
})
@Entity
public class Reclamacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String celular;

    @Column(nullable = false, length = 64)
    private String hashDoCelular;

    @Column(nullable = false, length = 4000)
    private String reclamacao;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate dataRegistro;

    @Deprecated
    /**
     * @deprecated uso exclusivo do Hibernate
     */
    public Reclamacao() {
    }

    public Reclamacao(String nome, String email, String celular, String reclamacao) {
        this.nome = nome;
        this.email = email;
        this.celular = CelularUtil.anonymize(celular);
        this.hashDoCelular = CelularUtil.hash(celular);
        this.reclamacao = reclamacao;
    }

    public Long getId() {
        return id;
    }
}
