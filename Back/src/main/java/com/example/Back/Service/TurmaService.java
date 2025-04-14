package com.example.Back.Service;

import com.example.Back.entity.Turma;
import com.example.Back.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository turmaRepository;


    public String salvarTurma(Turma turma) {
        turmaRepository.save(turma);
        return "Turma cadastrada";
    }

    public List<Turma> listarTurmas() {
        return turmaRepository.findAll();
    }

    public String atualizarTurma(Long id, Turma turma) {
        if(turmaRepository.existsById(id)){
            Turma turmaexistente = turmaRepository.findById(id).get();
            turmaexistente.setNome(turma.getNome());
            turmaexistente.setProfessor(turma.getProfessor());
            turmaRepository.save(turmaexistente);
            return "Atualizado com sucesso";
        }
        return "Erro ao atualizar";
    }

    public String deletarTurma(Long id) {
        if(turmaRepository.existsById(id)){
            turmaRepository.deleteById(id);
            return "deletado com sucesso";
        }
        return "Erro ao deletar";
    }
}
