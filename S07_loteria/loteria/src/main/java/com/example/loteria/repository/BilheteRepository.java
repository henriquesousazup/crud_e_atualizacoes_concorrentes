package com.example.loteria.repository;

import com.example.loteria.model.Bilhete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BilheteRepository extends JpaRepository<Bilhete, Long> {
    boolean existsByHashDoCelularAndNumeroDaSorteAndSorteioId(String hashDoCelular, Integer numeroDaSorte, Long sorteioId);
}
