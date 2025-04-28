package com.example.Back.Service;

import com.example.Back.dto.AlunoDTO;
import com.example.Back.dto.TurmaDTO;
import com.example.Back.entity.Turma;
import com.example.Back.entity.Atividade;
import com.example.Back.entity.Aluno;
import com.example.Back.repository.AlunoRepository;
import com.example.Back.repository.AtividadeRepository;
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

    @Autowired
    private AtividadeRepository atividadeRepository;

    // Salvar Turma
    public String salvarTurma(TurmaDTO turmadto) {
        turmaRepository.save(toEntity(turmadto));  // Salvando a turma sem a relação com o Professor
        return "Turma cadastrada";
    }

    // Listar todas as turmas
    public List<TurmaDTO> listarTurmas() {
        return turmaRepository.findAll().stream()
                .map(this::toTurmaDTO)
                .collect(Collectors.toList());
    }

    // Atualizar uma turma
    public String atualizarTurma(Long id, TurmaDTO turmadto) {
        if (turmaRepository.existsById(id)) {
            Turma turmaExistente = turmaRepository.findById(id).get();
            turmaExistente.setNome(turmadto.getNome());
            turmaExistente.setSigla(turmadto.getSigla());
            turmaExistente.setTermo(turmadto.getTermo());

            // Caso você precise atualizar os alunos ou as atividades, pode adicionar lógica aqui.

            turmaRepository.save(turmaExistente);
            return "Atualizado com sucesso";
        }
        return "Erro ao atualizar";
    }

    // Deletar uma turma
    public String deletarTurma(Long id) {
        if (turmaRepository.existsById(id)) {
            turmaRepository.deleteById(id);
            return "Deletado com sucesso";
        }
        return "Erro ao deletar";
    }

    // Listar turmas de um professor com base no email
    public List<TurmaDTO> listarTurmasPorProfessor(String email) {
        // Como você removeu o relacionamento com o professor, talvez essa função não seja mais necessária,
        // ou precisará de ajustes, como verificar por outras condições.
        return null;  // Retornar null ou outra lógica conforme sua necessidade
    }

    // Listar alunos por turma
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

    // Converte a entidade Turma para TurmaDTO
    private TurmaDTO toTurmaDTO(Turma turma) {
        List<Aluno> alunos = turma.getAlunos();  // Pega a lista de alunos
        List<Atividade> atividades = turma.getAtividades();  // Pega a lista de atividades

        // Cria o TurmaDTO com as listas de alunos e atividades
        return new TurmaDTO(
                turma.getId(),
                turma.getNome(),
                turma.getSigla(),
                turma.getTermo(),
                alunos,
                atividades
        );
    }

    // Converte o TurmaDTO para a entidade Turma
    private Turma toEntity(TurmaDTO dto) {
        Turma turma = new Turma();
        turma.setId(dto.getId());
        turma.setNome(dto.getNome());
        turma.setSigla(dto.getSigla());
        turma.setTermo(dto.getTermo());

        // O relacionamento com as atividades e alunos pode ser adicionado aqui, se necessário
        // Exemplo: turma.setAtividades(atividadeRepository.findAllByTurmaId(dto.getId()));

        return turma;
    }
}
