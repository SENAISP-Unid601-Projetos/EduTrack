package com.example.Back.Service;

import com.example.Back.entity.Professor;
import com.example.Back.entity.Turma;
import com.example.Back.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    @Autowired
    ProfessorRepository professorRepository;

    public String salvarProfessor(Professor professor) {
        professorRepository.save(professor);
        return "Professor cadastrado";
    }

    public List<Professor> listarProfessor() {
        return professorRepository.findAll();
    }

    public String atualizarTurma(Long id, Professor professor) {
        if(professorRepository.existsById(id)){
            Professor professorExistente = professorRepository.findById(id).get();
            professorExistente.setNome(professor.getNome());
            professorExistente.setEmail(professor.getEmail());
            professorExistente.setSenha(professor.getSenha());
            professorRepository.save(professorExistente);
            return "Atualizado com sucesso";
        }
        return "Erro ao atualizar";
    }

    public String deletarTurma(Long id) {
        if(professorRepository.existsById(id)){
            professorRepository.deleteById(id);
            return "deletado com sucesso";
        }
        return "Erro ao deletar";
    }

    public Professor professorPorEmail(String email) {
        if(professorRepository.existsByEmail(email)){
            return professorRepository.findByEmail(email);
        }
        return null;
    }
}
