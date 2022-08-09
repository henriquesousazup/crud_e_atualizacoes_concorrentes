package com.example.zupedustore.model;

import javax.persistence.*;

@Entity
public class Aplicativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String link;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "aplicativo")
    private QuantidadeDeDownloads quantidadeDeDownloads;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "aplicativo")
    private QuantidadeDeLikes quantidadeDeLikes;

    @Version
    private int versao;

    @Deprecated
    /**
     * @deprecated uso exclusivo do Hibernate
     */
    public Aplicativo() {
    }

    public Aplicativo(String nome, String descricao, String link) {
        this.nome = nome;
        this.descricao = descricao;
        this.link = link;
        this.quantidadeDeDownloads = new QuantidadeDeDownloads(this);
        this.quantidadeDeLikes = new QuantidadeDeLikes(this);
    }

    public Long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public void incrementarLike() {
        this.quantidadeDeLikes.incrementarQuantidade();
    }

    public void incrementarDownload() {
        this.quantidadeDeDownloads.incrementarQuantidade();
    }
}
