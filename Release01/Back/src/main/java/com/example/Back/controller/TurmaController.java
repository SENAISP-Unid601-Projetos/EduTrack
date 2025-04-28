package com.example.Back.controller;

import com.example.Back.Service.TurmaService;
import com.example.Back.dto.AlunoDTO;
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
    public String salvarTurma(@RequestBody TurmaDTO turmadto) {
        return turmaService.salvarTurma(turmadto);
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarTurma(@PathVariable Long id, @RequestBody TurmaDTO turmadto) {
        return turmaService.atualizarTurma(id, turmadto);
    }

    @GetMapping
    public List<TurmaDTO> listarTurmas() {
        return turmaService.listarTurmas();
    }

    @DeleteMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        return turmaService.deletarTurma(id);
    }
}
