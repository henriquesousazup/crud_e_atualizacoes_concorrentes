package com.example.alunos.repository;

import com.example.alunos.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface AlunoRepository extends JpaRepository<Aluno,Long> {
    boolean existsByNumeroDeMatriculaAndDataDeMatricula(String numeroDeMatricula, LocalDate dataDeMatricula);
}
