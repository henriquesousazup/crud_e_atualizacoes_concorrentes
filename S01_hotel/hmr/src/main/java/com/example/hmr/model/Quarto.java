package com.example.hmr.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valorDiaria;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoQuarto tipoQuarto;

    @Column(nullable = false)
    private Boolean isReservaAtiva = false;

    @Deprecated
    /**
     * @deprecated uso exclusivo do hibernate
     */
    public Quarto() {
    }

    public Quarto(String descricao, BigDecimal valorDiaria, TipoQuarto tipoQuarto, Boolean isReservaAtiva) {
        this.descricao = descricao;
        this.valorDiaria = valorDiaria;
        this.tipoQuarto = tipoQuarto;
        this.isReservaAtiva = isReservaAtiva;
    }

    public Long getId() {
        return id;
    }

    public void ativarReserva() {
        this.isReservaAtiva = true;
    }

    public Boolean getReservaAtiva() {
        return isReservaAtiva;
    }
}
