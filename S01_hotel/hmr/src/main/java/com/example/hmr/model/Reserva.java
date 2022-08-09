package com.example.hmr.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private Quarto quartoReservado;

    @Column(nullable = false)
    private LocalDate checkIn;

    @Column(nullable = false)
    private LocalDate checkOut;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime instanteDaReserva;

    @Deprecated
    /**
     * @deprecated uso exclusivo do hibernate
     */
    public Reserva() {
    }

    public Reserva(Quarto quartoReservado, LocalDate checkIn, LocalDate checkOut) {
        this.quartoReservado = quartoReservado;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Long getId() {
        return id;
    }

    public void setQuartoReservado(Quarto quartoReservado) {
        this.quartoReservado = quartoReservado;
    }
}
