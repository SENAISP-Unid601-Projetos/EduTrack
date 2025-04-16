package com.senai.FrontBack.FrontBack_Mykael_Victor.Repository;

import com.senai.FrontBack.FrontBack_Mykael_Victor.Entity.ProfessorEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntitiy, Long> {
    Optional<ProfessorEntitiy> findByEmail(String email);
}
