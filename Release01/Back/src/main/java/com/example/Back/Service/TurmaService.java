package com.example.Back.Service;

import com.example.Back.dto.AlunoDTO;
import com.example.Back.dto.ProfessorDTO;
import com.example.Back.dto.TurmaDTO;
import com.example.Back.entity.Professor;
import com.example.Back.entity.Turma;
import com.example.Back.repository.AlunoRepository;
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
    private AlunoRepository alunoRepository;

    public String salvarTurma(TurmaDTO turmadto) {
        turmaRepository.save(toEntity(turmadto));
        return "Turma cadastrada";
    }

    public List<TurmaDTO> listarTurmas() {
        return turmaRepository.findAll().stream().map(this::toTurmaDTO).collect(Collectors.toList());
    }

    public String atualizarTurma(Long id, TurmaDTO turmadto) {
        if(turmaRepository.existsById(id)){
            Turma turmaexistente = turmaRepository.findById(id).get();
            turmaexistente.setNome(turmadto.getNome());
            turmaexistente.setSigla(turmadto.getSigla());
            turmaexistente.setTermo(turmadto.getTermo());
            turmaRepository.save(turmaexistente);
            return "Atualizado com sucesso";
        }
        return "Erro ao atualizar";
    }

    public String deletarTurma(Long id) {
        if (turmaRepository.existsById(id)) {
            turmaRepository.deleteById(id);
            return "Turma deletada com sucesso";
        }
        return "Turma não encontrada";
    }

    public List<AlunoDTO> listarAlunosPorTurma(Long idTurma) {
        return alunoRepository.findByTurmaId(idTurma).stream()
                .map(aluno -> new AlunoDTO(
                        aluno.getId(),
                        aluno.getCpf(),
                        aluno.getNome(),
                        aluno.getTurma() != null ? aluno.getTurma().getId() : null
                ))
                .collect(Collectors.toList());
    }

    private TurmaDTO toTurmaDTO(Turma turma) {
        return new TurmaDTO(
                turma.getId(),
                turma.getNome(),
                turma.getSigla(),
                turma.getTermo()
        );
    }

    private Turma toEntity(TurmaDTO dto) {
        Turma turma = new Turma();
        turma.setId(dto.getId());
        turma.setNome(dto.getNome());
        turma.setSigla(dto.getSigla());
        turma.setTermo(dto.getTermo());
        return turma;
    }



}
