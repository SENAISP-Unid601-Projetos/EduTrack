package com.example.Back.dto;

import com.example.Back.entity.Atividade;
import com.example.Back.entity.Aluno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaDTO {

    private Long id;
    private String nome;
    private String sigla;  // Atributo de sigla
    private String termo;  // Atributo de termo
    private List<Aluno> alunos;  // Lista de alunos (relacionamento)
    private List<Atividade> atividades;  // Lista de atividades (relacionamento)

}
