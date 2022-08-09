package com.example.pacotededados.repository;

import com.example.pacotededados.model.PacoteDeDados;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacoteDeDadosRepository extends JpaRepository<PacoteDeDados, Long> {
    boolean existsByHashDoCpf(String cpf);
}
