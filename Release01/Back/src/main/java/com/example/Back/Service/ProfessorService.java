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

    public String salvarProfessor(ProfessorDTO professorDTO) {
        Professor professor = toEntity(professorDTO);
        professorRepository.save(professor);
        return "Professor cadastrado com sucesso";
    }

    public List<ProfessorDTO> listarProfessores() {
        return professorRepository.findAll()
                .stream()
                .map(this::toProfessorDTO)
                .collect(Collectors.toList());
    }

    public String atualizarProfessor(Long id, ProfessorDTO professorDTO) {
        if (professorRepository.existsById(id)) {
            Professor professorExistente = professorRepository.findById(id).get();
            professorExistente.setNome(professorDTO.getNome());
            professorExistente.setEmail(professorDTO.getEmail());
            professorExistente.setSenha(professorDTO.getSenha());
            professorRepository.save(professorExistente);
            return "Professor atualizado com sucesso";
        }
        return "Professor não encontrado";
    }

    public String deletarProfessor(Long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return "Professor deletado com sucesso";
        }
        return "Professor não encontrado";
    }

    private ProfessorDTO toProfessorDTO(Professor professor) {
        return new ProfessorDTO(
                professor.getId(),
                professor.getNome(),
                professor.getEmail(),
                professor.getSenha()
        );
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
