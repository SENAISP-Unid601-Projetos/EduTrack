package com.example.Back.controller;

import com.example.Back.Service.ProfessorService;
import com.example.Back.dto.LoginDTO;
import com.example.Back.dto.ProfessorDTO;
import com.example.Back.entity.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public String salvar(@RequestBody ProfessorDTO professorDTO) {
        return professorService.salvarProfessor(professorDTO);
    }

    @GetMapping
    public List<ProfessorDTO> listar() {
        return professorService.listarProfessores();
    }

    @PutMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO) {
        return professorService.atualizarProfessor(id, professorDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        return professorService.deletarProfessor(id);
    }

}
