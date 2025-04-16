package com.example.Back.controller;

import com.example.Back.Service.TurmaService;
import com.example.Back.dto.TurmaDTO;
import com.example.Back.entity.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
    @Autowired
    private TurmaService turmaService;

    @PostMapping
    public ResponseEntity<String> criarTurma(@RequestBody TurmaDTO turma) {
        try {
            String resultado = turmaService.salvarTurma(turma);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<TurmaDTO>> listarTurmas() {
        try {
            return ResponseEntity.ok(turmaService.listarTurmas());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<List<TurmaDTO>> listarTurmasProfessores(@PathVariable String email) {
        try {
            return ResponseEntity.ok(turmaService.listarTurmasporProfessor(email));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarTurma(@PathVariable Long id, @RequestBody TurmaDTO turma) {
        try {
            String resultado = turmaService.atualizarTurma(id, turma);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarTurma(@PathVariable Long id) {
        try {
            String resultado = turmaService.deletarTurma(id);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
