package com.example.Back.repository;

import com.example.Back.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Professor findByEmail(String email);

    boolean existsByEmail(String email);
}
