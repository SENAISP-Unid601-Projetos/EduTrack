package com.senai.FrontBack.FrontBack_Mykael_Victor.Controller;

import com.senai.FrontBack.FrontBack_Mykael_Victor.DTO.ProfessorDTO;
import com.senai.FrontBack.FrontBack_Mykael_Victor.Entity.ProfessorEntitiy;
import com.senai.FrontBack.FrontBack_Mykael_Victor.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("professor")
public class ProfessorController {
    @Autowired
    ProfessorRepository ProfRep;

    @PostMapping("/post")
    public ResponseEntity<Boolean> postProfessor(@RequestBody ProfessorEntitiy p) {
        ProfRep.save(p);
        return ResponseEntity.ok(true);
    }
    @GetMapping("/get")
    public ResponseEntity<List<ProfessorEntitiy>> getProfessor() {
        List<ProfessorEntitiy> Professor = ProfRep.findAll();
        return new ResponseEntity(Professor, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody ProfessorDTO loginRequest) {
        String email = loginRequest.getEmail();
        String senha = loginRequest.getSenha();

        if (email == null || senha == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email e senha são obrigatórios!");
        }

        Optional<ProfessorEntitiy> professorOpt = ProfRep.findByEmail(email);
        if (professorOpt.isPresent() && professorOpt.get().getSenha().equals(senha)) {
            return ResponseEntity.ok("{\"message\": \"Login bem-sucedido! Bem-vindo, " + email + "\"}");

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos!");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteProfessor(@PathVariable Long id) {
        ProfRep.deleteById(id);
        return ResponseEntity.ok(true);
    }

}
