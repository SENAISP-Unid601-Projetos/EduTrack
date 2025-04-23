package com.example.Back.repository;

import com.example.Back.entity.Atividade;
import com.example.Back.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtividadeRepository extends JpaRepository<Atividade,Long> {

    List<Atividade> findAllByTurma(Turma turma);
}
