package com.example.Back.dto;

import com.example.Back.entity.Aluno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO {
    private Long id;
    private String cpf;
    private String nome;
    private Long id_turma;


}
