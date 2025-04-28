package com.example.Back.controller;

import com.example.Back.Service.TurmaService;
import com.example.Back.dto.TurmaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    // Criar Turma
    @PostMapping
    public ResponseEntity<String> criarTurma(@RequestBody TurmaDTO turma) {
        try {
            String resultado = turmaService.salvarTurma(turma);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Listar todas as turmas
    @GetMapping
    public ResponseEntity<List<TurmaDTO>> listarTurmas() {
        try {
            return ResponseEntity.ok(turmaService.listarTurmas());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Ajuste na listagem de turmas (não mais por email de professor)
    // Retorna todas as turmas
    @GetMapping("/listar")
    public ResponseEntity<List<TurmaDTO>> listarTodasTurmas() {
        try {
            return ResponseEntity.ok(turmaService.listarTurmas());  // Retorna todas as turmas
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Atualizar turma
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarTurma(@PathVariable Long id, @RequestBody TurmaDTO turma) {
        try {
            String resultado = turmaService.atualizarTurma(id, turma);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Deletar turma
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarTurma(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(turmaService.deletarTurma(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro ao deletar turma.");
        }
    }
}
