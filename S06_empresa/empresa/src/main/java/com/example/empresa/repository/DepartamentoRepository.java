package com.example.empresa.repository;

import com.example.empresa.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    boolean existsBySigla(String sigla);
}
