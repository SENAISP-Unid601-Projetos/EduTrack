package com.example.Back.Service;

import com.example.Back.entity.Professor;
import com.example.Back.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    @Autowired
    ProfessorRepository professorRepository;

    public String salvarProfessor(Professor professor) {
        professorRepository.save(professor);
        return "O professor foi salvo";
    }

    public List<Professor> listarProfessor() {
        return professorRepository.findAll();
    }
}
