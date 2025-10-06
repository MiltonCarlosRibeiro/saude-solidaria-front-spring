package com.saudesolidaria.app.controller;

import com.saudesolidaria.app.model.DoadorCorporativo;
import com.saudesolidaria.app.model.DoadorIndividual;
import com.saudesolidaria.app.repo.DoacaoRepository;
import com.saudesolidaria.app.repo.DoadorCorporativoRepository;
import com.saudesolidaria.app.repo.DoadorIndividualRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DoadoresController {
  private final DoadorIndividualRepository indRepo;
  private final DoadorCorporativoRepository corpRepo;
  private final DoacaoRepository doacaoRepo; // Injetar repositório de doação

  // Construtor atualizado
  public DoadoresController(DoadorIndividualRepository indRepo, DoadorCorporativoRepository corpRepo, DoacaoRepository doacaoRepo) {
    this.indRepo = indRepo;
    this.corpRepo = corpRepo;
    this.doacaoRepo = doacaoRepo;
  }

  // MÉTODOS GET, POST, PUT (sem alterações)
  @GetMapping("/doadores-individuais") public List<DoadorIndividual> individuais(){ return indRepo.findAll(); }
  @GetMapping("/doadores-individuais/{id}") public ResponseEntity<DoadorIndividual> individualOne(@PathVariable String id) { return indRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
  @PostMapping("/doadores-individuais") public DoadorIndividual createIndividual(@RequestBody DoadorIndividual newDoador) { return indRepo.save(newDoador); }
  @PutMapping("/doadores-individuais/{id}") public ResponseEntity<DoadorIndividual> updateIndividual(@PathVariable String id, @RequestBody DoadorIndividual updatedDoador) { if (!indRepo.existsById(id)) { return ResponseEntity.notFound().build(); } updatedDoador.setId(id); return ResponseEntity.ok(indRepo.save(updatedDoador)); }
  @GetMapping("/doadores-corporativos") public List<DoadorCorporativo> corporativos(){ return corpRepo.findAll(); }
  @GetMapping("/doadores-corporativos/{id}") public ResponseEntity<DoadorCorporativo> corporativoOne(@PathVariable String id) { return corpRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
  @PostMapping("/doadores-corporativos") public DoadorCorporativo createCorporativo(@RequestBody DoadorCorporativo newDoador) { return corpRepo.save(newDoador); }
  @PutMapping("/doadores-corporativos/{id}") public ResponseEntity<DoadorCorporativo> updateCorporativo(@PathVariable String id, @RequestBody DoadorCorporativo updatedDoador) { if (!corpRepo.existsById(id)) { return ResponseEntity.notFound().build(); } updatedDoador.setId(id); return ResponseEntity.ok(corpRepo.save(updatedDoador)); }

  // MÉTODOS DELETE (Atualizados com lógica de cascata)
  @DeleteMapping("/doadores-individuais/{id}")
  @Transactional
  public ResponseEntity<Void> deleteIndividual(@PathVariable String id) {
    if (!indRepo.existsById(id)) { return ResponseEntity.notFound().build(); }
    doacaoRepo.deleteByDoadorId(id);
    indRepo.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/doadores-corporativos/{id}")
  @Transactional
  public ResponseEntity<Void> deleteCorporativo(@PathVariable String id) {
    if (!corpRepo.existsById(id)) { return ResponseEntity.notFound().build(); }
    doacaoRepo.deleteByDoadorId(id);
    corpRepo.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}