package com.example.Back.Service;

import com.example.Back.dto.ProfessorDTO;
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
        if (professorRepository.existsByEmail(professor.getEmail())){
            return "Já existe um professor cadastrado com esse e-mail";
        }
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

    private ProfessorDTO toProfessorDTO(Professor professor) {
        return new ProfessorDTO(professor.getId(), professor.getNome(), professor.getEmail(), professor.getSenha());
    }

    private Professor toEntity(ProfessorDTO dto) {
        Professor professor = new Professor();
        professor.setId(dto.getId());
        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setSenha(dto.getSenha());
        return professor;
    }
}
