package com.example.reclamacoes.repository;

import com.example.reclamacoes.model.Reclamacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamacaoRepository extends JpaRepository<Reclamacao, Long> {
    boolean existsByHashDoCelularAndReclamacao(String hashDoCelular, String reclamacao);
}
