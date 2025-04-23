package com.example.Back.Service;

import com.example.Back.dto.AlunoDTO;

import com.example.Back.entity.Aluno;

import com.example.Back.repository.AlunoRepository;

import com.example.Back.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    public String salvarAluno(AlunoDTO alunoDTO) {
        if(turmaRepository.existsById(alunoDTO.getId_turma())){
            alunoRepository.save(toEntity(alunoDTO));
            return "Aluno cadastrado";
        }
        return "Turma não existente";
    }

    public List<AlunoDTO> listarAlunos() {
        return alunoRepository.findAll().stream().map(this::toAlunoDTO).collect(Collectors.toList());
    }

    public String atualizarAluno(Long id, AlunoDTO alunoDTO) {
        if(alunoRepository.existsById(id)){
            if(turmaRepository.existsById(alunoDTO.getId_turma())){
                Aluno alunoExistente = alunoRepository.findById(id).get();
                alunoExistente.setNome(alunoDTO.getNome());
                alunoExistente.setTurma(turmaRepository.findById(alunoDTO.getId_turma()).get());
                alunoRepository.save(alunoExistente);
                return "Atualizado com sucesso";
            }
        }
        return "Erro ao atualizar";
    }

    public String deletarAluno(Long id) {
        if(alunoRepository.existsById(id)){
            alunoRepository.deleteById(id);
            return "deletado com sucesso";
        }
        return "Erro ao deletar";
    }


    private AlunoDTO toAlunoDTO(Aluno aluno) {
        return new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getCpf(), aluno.getTurma().getId());
    }

    private Aluno toEntity(AlunoDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setId(dto.getId());
        aluno.setNome(dto.getNome());
        aluno.setCpf(dto.getCpf());
        aluno.setTurma(turmaRepository.findById(dto.getId_turma()).get());
        return aluno;
    }
}
