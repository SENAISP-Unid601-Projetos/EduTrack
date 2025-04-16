package com.example.Back.controller;

import com.example.Back.Service.AtividadeService;
import com.example.Back.dto.AtividadeDTO;
import com.example.Back.entity.Atividade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {
    @Autowired
    private AtividadeService atividadeService;

    @PostMapping
    public ResponseEntity<String> criarAtividade(@RequestBody AtividadeDTO atividade){
        try{
            String resultado = atividadeService.salvarAtividade(atividade);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<AtividadeDTO>> listarAtividades(){
        try{
            return ResponseEntity.ok(atividadeService.listarAtividade());
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<AtividadeDTO>> listarAtividadesPorTurma(@PathVariable Long id){
        try{
            return ResponseEntity.ok(atividadeService.listarAtividadeporTurma(id));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarAtividade(@PathVariable Long id, @RequestBody AtividadeDTO atividade) {
        try {
            String resultado = atividadeService.atualizarAtividade(id, atividade);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarAtividade(@PathVariable Long id) {
        try {
            String resultado = atividadeService.deletarAtividade(id);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
