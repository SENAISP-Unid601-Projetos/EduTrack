package com.senai.FrontBack.FrontBack_Mykael_Victor.Repository;

import com.senai.FrontBack.FrontBack_Mykael_Victor.Entity.AtividadeEntity;
import com.senai.FrontBack.FrontBack_Mykael_Victor.Entity.TurmaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtividadeRepository extends JpaRepository<AtividadeEntity, Long> {
    Optional<AtividadeEntity> findByNumeroAtividade(Long numeroAtividade);

}
