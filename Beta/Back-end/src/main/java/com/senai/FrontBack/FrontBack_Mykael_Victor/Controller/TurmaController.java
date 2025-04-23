package com.senai.FrontBack.FrontBack_Mykael_Victor.Controller;

import com.senai.FrontBack.FrontBack_Mykael_Victor.Entity.TurmaEntity;
import com.senai.FrontBack.FrontBack_Mykael_Victor.Repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("turma")
public class TurmaController {
    @Autowired
    TurmaRepository TurmaRep;


    @PostMapping("/post")
    public ResponseEntity<Boolean> postTurma(@RequestBody TurmaEntity t) {
        TurmaRep.save(t);
        return ResponseEntity.ok(true);
    }
    @GetMapping("/get")
    public  ResponseEntity<List<TurmaEntity>> getTurma () {
        List<TurmaEntity> Turma = TurmaRep.findAll();
        return new ResponseEntity(Turma, HttpStatus.OK);

    }
    @GetMapping("/get{numeroTurma}")
    public ResponseEntity<List<TurmaEntity>> getTurmaByNumero(@RequestParam long numeroTurma) {
        Optional<TurmaEntity> Turma = TurmaRep.findByNumeroTurma(numeroTurma);
        return new ResponseEntity(Turma, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{numeroTurma}")
    public ResponseEntity<Boolean> deleteTurma(@PathVariable long numeroTurma) {
        TurmaRep.deleteById(numeroTurma);
        return ResponseEntity.ok(true);
    }

}
