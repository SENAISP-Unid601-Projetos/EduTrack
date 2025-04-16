package com.example.Back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String nome;

    private String email;

    private String senha;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Turma> turmas;

}
