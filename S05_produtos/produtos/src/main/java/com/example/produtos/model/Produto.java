package com.example.produtos.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_CODIGO_PRODUTO", columnNames = { "codigo" })
})
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 6)
    private String codigo;

    @Column(nullable = false)
    private BigDecimal preco;

    @Deprecated
    /**
     * @deprecated para o Hibernate
     */
    public Produto() {
    }

    public Produto(String nome, String codigo, BigDecimal preco) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }
}
