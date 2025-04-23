package com.example.Back.Service;

import com.example.Back.dto.AtividadeDTO;
import com.example.Back.dto.TurmaDTO;
import com.example.Back.entity.Atividade;
import com.example.Back.entity.Turma;
import com.example.Back.repository.AtividadeRepository;
import com.example.Back.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    public String salvarAtividade(AtividadeDTO atividadedto) {
        if(turmaRepository.existsById(atividadedto.getId_turma())){
            atividadeRepository.save(toEntity(atividadedto));
            return "Atividade cadastrada";
        }
        return "Turma não encontrada";
    }

    public List<AtividadeDTO> listarAtividade() {
        return atividadeRepository.findAll().stream()
                .map(this::toAtividadeDTO)
                .collect(Collectors.toList());
    }

    public String atualizarAtividade(Long id, AtividadeDTO atividadedto) {
        if(atividadeRepository.existsById(id)){
            if(turmaRepository.existsById(atividadedto.getId_turma())){
                Atividade atividadeExistente = atividadeRepository.findById(id).get();
                atividadeExistente.setDescricao(atividadedto.getDescricao());
                atividadeExistente.setTurma(turmaRepository.findById(atividadedto.getId_turma()).get());
                atividadeRepository.save(atividadeExistente);
                return "Atualizado com sucesso";
            }
        }
        return "Erro ao atualizar";
    }

    public String deletarAtividade(Long id) {
        if(atividadeRepository.existsById(id)){
            atividadeRepository.deleteById(id);
            return "deletado com sucesso";
        }
        return "Erro ao deletar";
    }

    public List<AtividadeDTO> listarAtividadeporTurma(Long idTurma) {
        if(turmaRepository.existsById(idTurma)) {
            Turma turma = turmaRepository.findById(idTurma).orElseThrow(() -> new RuntimeException("Turma não encontrada"));
            return atividadeRepository.findAllByTurma(turma).stream()
                    .map(this::toAtividadeDTO)
                    .collect(Collectors.toList());
        }
        return null;
    }

    private AtividadeDTO toAtividadeDTO(Atividade atividade) {
        return new AtividadeDTO(atividade.getId(), atividade.getNome(), atividade.getDescricao(), atividade.getTurma().getId());
    }

    private Atividade toEntity(AtividadeDTO dto) {
        Atividade atividade = new Atividade();
        atividade.setId(dto.getId());
        atividade.setNome(dto.getNome());
        atividade.setDescricao(dto.getDescricao());
        atividade.setTurma(turmaRepository.findById(dto.getId_turma()).get());
        return atividade;
    }
}
