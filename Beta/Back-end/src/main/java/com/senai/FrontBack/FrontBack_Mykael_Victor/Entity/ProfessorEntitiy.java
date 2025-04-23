package com.senai.FrontBack.FrontBack_Mykael_Victor.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data

public class ProfessorEntitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)

    Long id;
    String nome;
    String email;
    String senha;

}
