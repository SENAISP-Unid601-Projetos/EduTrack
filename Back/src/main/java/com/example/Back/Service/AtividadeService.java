package com.example.Back.Service;

import com.example.Back.entity.Atividade;
import com.example.Back.entity.Turma;
import com.example.Back.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    public String salvarAtividade(Atividade atividade) {
        atividadeRepository.save(atividade);
        return "Atividade cadastrada";
    }

    public List<Atividade> listarAtividade() {
        return atividadeRepository.findAll();
    }

    public String atualizarAtividade(Long id, Atividade atividade) {
        if(atividadeRepository.existsById(id)){
            Atividade atividadeExistente = atividadeRepository.findById(id).get();
            atividadeExistente.setDescricao(atividade.getDescricao());
            atividadeRepository.save(atividadeExistente);
            return "Atualizado com sucesso";
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

    public List<Atividade> listarAtividadeporTurma(Long id) {

    }
}
