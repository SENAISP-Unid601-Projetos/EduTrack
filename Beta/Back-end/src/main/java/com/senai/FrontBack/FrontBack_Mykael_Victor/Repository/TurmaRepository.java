package com.senai.FrontBack.FrontBack_Mykael_Victor.Repository;

import com.senai.FrontBack.FrontBack_Mykael_Victor.Entity.ProfessorEntitiy;
import com.senai.FrontBack.FrontBack_Mykael_Victor.Entity.TurmaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<TurmaEntity, Long> {
    Optional<TurmaEntity> findByNumeroTurma(Long numeroTurma);

}
