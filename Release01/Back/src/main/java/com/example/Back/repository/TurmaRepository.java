package com.example.Back.repository;

import com.example.Back.entity.Professor;
import com.example.Back.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

   
}
