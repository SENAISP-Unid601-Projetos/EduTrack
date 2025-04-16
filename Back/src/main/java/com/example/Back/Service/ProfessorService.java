package com.example.Back.Service;

import com.example.Back.dto.LoginDTO;
import com.example.Back.dto.ProfessorDTO;
import com.example.Back.entity.Professor;
import com.example.Back.entity.Turma;
import com.example.Back.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public String salvarProfessor(ProfessorDTO professordto) {
        if (professorRepository.existsByEmail(professordto.getEmail())){
            return "Já existe um professor cadastrado com esse e-mail";
        }
        professorRepository.save(toEntity(professordto));
        return "Professor cadastrado";
    }

    public List<ProfessorDTO> listarProfessor() {
        return professorRepository.findAll().stream()
                .map(this::toProfessorDTO)
                .collect(Collectors.toList());
    }

    public String atualizarTurma(Long id, ProfessorDTO professordto) {
        if(professorRepository.existsById(id)){
            Professor professorExistente = professorRepository.findById(id).get();
            professorExistente.setNome(professordto.getNome());
            professorExistente.setEmail(professordto.getEmail());
            professorExistente.setSenha(professordto.getSenha());
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
            return professorRepository.findByEmail(email).get();
        }
        return null;
    }

    public boolean authenticateUser(LoginDTO loginDTO) {
        return professorRepository.findByEmail(loginDTO.getEmail()).map(professor -> professor.getSenha().equals(loginDTO.getSenha())).orElse(false);
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
