package com.example.Back.controller;

import com.example.Back.dto.CadastroDTO;
import com.example.Back.dto.LoginDTO;
import com.example.Back.entity.Usuario;
import com.example.Back.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/API")
@CrossOrigin(origins = "http://10.110.12.52:8080")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            Usuario usuario = usuarioService.autenticar(loginDTO.getEmail(), loginDTO.getSenha());
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro ao autenticar: " + e.getMessage());
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody CadastroDTO cadastroDTO) {
        try {
            Usuario usuario = usuarioService.cadastrar(cadastroDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar: " + e.getMessage());
        }
    }
}