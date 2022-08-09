package com.example.ingressos.repository;

import com.example.ingressos.model.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
    boolean existsByNumeroAndDataEvento(Integer numero, LocalDate dataEvento);
}
