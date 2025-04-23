package com.example.Back.controller;

import com.example.Back.Service.AlunoService;
import com.example.Back.dto.AlunoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")

public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public String salvarAluno(@RequestBody AlunoDTO alunoDTO) {
        return alunoService.salvarAluno(alunoDTO);
    }

    @GetMapping
    public List<AlunoDTO> listarAlunos() {
        return alunoService.listarAlunos();
    }

    @PutMapping("/{id}")
    public String atualizarAluno(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO) {
        return alunoService.atualizarAluno(id, alunoDTO);
    }

    @DeleteMapping("/{id}")
    public String deletarAluno(@PathVariable Long id) {
        return alunoService.deletarAluno(id);
    }
}
