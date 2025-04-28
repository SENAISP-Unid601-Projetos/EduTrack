package com.example.Back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String nome;
    private String sigla;  // Atributo de sigla
    private String termo;   // Atributo de termo



    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Atividade> atividades;  // Relacionamento com Atividades

    @OneToMany(mappedBy = "turma")
    @JsonIgnore
    private List<Aluno> alunos;  // Relacionamento com Alunos

}
