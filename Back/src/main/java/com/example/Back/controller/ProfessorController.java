package com.example.Back.controller;

import com.example.Back.Service.ProfessorService;
import com.example.Back.entity.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<String> criarProfessor(@RequestBody Professor professor){
        try{
            String resultado = professorService.salvarProfessor(professor);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Professor>> listarProfessores(){
        try{
            return ResponseEntity.ok(professorService.listarProfessor());
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("get/{email}")
    public ResponseEntity<Professor> professorEmail(@PathVariable String email){
        try{
            return ResponseEntity.ok(professorService.professorPorEmail());
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarProfessor(@PathVariable Long id, @RequestBody Professor professor) {
        try {
            String resultado = professorService.atualizarTurma(id, professor);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarTurma(@PathVariable Long id) {
        try {
            String resultado = professorService.deletarTurma(id);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
