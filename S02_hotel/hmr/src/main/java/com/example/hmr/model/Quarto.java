package com.example.hmr.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Reserva> reservas = new ArrayList<>();

    @Version
    private int versao;

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

    public void reservarQuarto(Reserva novaReserva) {

        if (getReservaAtiva()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Esse quarto já possui reserva ativa.");
        }

        novaReserva.setQuartoReservado(this);
        this.reservas.add(novaReserva);

        this.isReservaAtiva = true;
    }

    public Boolean getReservaAtiva() {
        return isReservaAtiva;
    }
}
