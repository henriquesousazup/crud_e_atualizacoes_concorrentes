package com.example.carros.repository;

import com.example.carros.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    boolean existsByPlaca(String placa);

    boolean existsByChassi(String chassi);
}
