package com.example.empresa.repository;

import com.example.empresa.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    boolean existsByTelefoneAndDepartamentoId(String telefone, Long departamentoId);
}
