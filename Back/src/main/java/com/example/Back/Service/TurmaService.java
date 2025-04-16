package com.example.Back.Service;

import com.example.Back.dto.ProfessorDTO;
import com.example.Back.dto.TurmaDTO;
import com.example.Back.entity.Professor;
import com.example.Back.entity.Turma;
import com.example.Back.repository.ProfessorRepository;
import com.example.Back.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public String salvarTurma(TurmaDTO turmadto) {
        if(professorRepository.existsById(turmadto.getId_professor())){
            turmaRepository.save(toEntity(turmadto));
            return "Turma cadastrada";
        }
        return "Professor não existente";
    }

    public List<TurmaDTO> listarTurmas() {
        return turmaRepository.findAll().stream().map(this::toTurmaDTO).collect(Collectors.toList());
    }

    public String atualizarTurma(Long id, TurmaDTO turmadto) {
        if(turmaRepository.existsById(id)){
            if(professorRepository.existsById(turmadto.getId_professor())){
                Turma turmaexistente = turmaRepository.findById(id).get();
                turmaexistente.setNome(turmadto.getNome());
                turmaexistente.setProfessor(professorRepository.findById(turmadto.getId_professor()).get());
                turmaRepository.save(turmaexistente);
                return "Atualizado com sucesso";
            }
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

    public List<TurmaDTO> listarTurmasporProfessor(String email) {
        if(professorRepository.existsByEmail(email)){
            return turmaRepository.findByProfessor(professorRepository.findByEmail(email).get()).stream().map(this::toTurmaDTO).collect(Collectors.toList());
        }
        return null;
    }

    private TurmaDTO toTurmaDTO(Turma turma) {
        return new TurmaDTO(turma.getId(), turma.getNome(), turma.getProfessor().getId());
    }

    private Turma toEntity(TurmaDTO dto) {
        Turma turma = new Turma();
        turma.setId(dto.getId());
        turma.setNome(dto.getNome());
        turma.setProfessor(professorRepository.findById(dto.getId_professor()).get());
        return turma;
    }

}
