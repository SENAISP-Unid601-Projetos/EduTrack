package com.senai.FrontBack.FrontBack_Mykael_Victor.Controller;

import com.senai.FrontBack.FrontBack_Mykael_Victor.Entity.AtividadeEntity;
import com.senai.FrontBack.FrontBack_Mykael_Victor.Repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("atividade")
public class AtividadeController {
    @Autowired
    AtividadeRepository AtvRep;

    @PostMapping("post")
    public ResponseEntity<Boolean> postAtv(@RequestBody AtividadeEntity atv) {
        AtvRep.save(atv);
        return ResponseEntity.ok(true);
    }
    @GetMapping("get")
    public ResponseEntity<List<AtividadeEntity>> getAtv() {
        List<AtividadeEntity> Atvd = AtvRep.findAll();
        return new ResponseEntity(Atvd, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{numeroAtividade}")
    public ResponseEntity<Boolean> deleteAtv(@PathVariable Long numeroAtividade) {
        AtvRep.deleteById(numeroAtividade);
        return ResponseEntity.ok(true);
    }
}
