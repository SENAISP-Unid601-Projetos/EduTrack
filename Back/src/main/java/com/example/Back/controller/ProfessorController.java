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
    private MessageSource messageSource;

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<String> criarProfessor(@RequestBody ProfessorDTO professor){
        try{
            String resultado = professorService.salvarProfessor(professor);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> listarProfessores(){
        try{
            return ResponseEntity.ok(professorService.listarProfessor());
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("get/{email}")
    public ResponseEntity<Professor> professorEmail(@PathVariable String email){
        try{
            return ResponseEntity.ok(professorService.professorPorEmail(email));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarProfessor(@PathVariable Long id, @RequestBody ProfessorDTO professor) {
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

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> authenticateUser(@RequestBody LoginDTO loginDTO, Locale locale) {
        boolean isAuthenticated = professorService.authenticateUser(loginDTO);

        Map<String, Object> response = new HashMap<>();

        if (isAuthenticated) {
            String successMessage = messageSource.getMessage("login.success", null, locale);
            response.put("status", "success");
            response.put("message", successMessage);
            return ResponseEntity.ok(response);
        } else {
            String failureMessage = messageSource.getMessage("login.failed", null, locale);
            response.put("status", "error");
            response.put("message", failureMessage);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

}
