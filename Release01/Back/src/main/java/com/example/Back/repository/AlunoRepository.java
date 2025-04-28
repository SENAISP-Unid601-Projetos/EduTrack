package com.example.Back.repository;


import com.example.Back.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByCpf(String cpf);


    List<Aluno> findByTurmaId(Long turmaId);



}
