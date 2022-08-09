package com.example.livraria.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = {
 @UniqueConstraint(name = "UK_LIVRO_ISBN", columnNames = { "isbn" })
})
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private LocalDate dataDePublicacao;

    @Deprecated
    /**
     * @deprecated Uso exclusivo do Hibernate
     */
    public Livro() {
    }

    public Livro(String titulo, String isbn, LocalDate dataDePublicacao) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.dataDePublicacao = dataDePublicacao;
    }

    public Long getId() {
        return id;
    }
}
