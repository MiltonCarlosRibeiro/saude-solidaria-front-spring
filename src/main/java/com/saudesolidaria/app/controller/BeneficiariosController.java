package com.saudesolidaria.app.controller;

import com.saudesolidaria.app.model.Beneficiario;
import com.saudesolidaria.app.repo.BeneficiarioRepository;
import com.saudesolidaria.app.repo.DoacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/beneficiarios")
public class BeneficiariosController {
  private final BeneficiarioRepository beneficiarioRepo;
  private final DoacaoRepository doacaoRepo; // Injetar repositório de doação

  // Construtor atualizado para receber os dois repositórios
  public BeneficiariosController(BeneficiarioRepository beneficiarioRepo, DoacaoRepository doacaoRepo) {
    this.beneficiarioRepo = beneficiarioRepo;
    this.doacaoRepo = doacaoRepo;
  }

  // Métodos GET, POST, PUT (sem alterações)
  @GetMapping public List<Beneficiario> all() { return beneficiarioRepo.findAll(); }
  @GetMapping("/{id}") public ResponseEntity<Beneficiario> one(@PathVariable String id) { return beneficiarioRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
  @PostMapping public Beneficiario create(@RequestBody Beneficiario newBeneficiario) { return beneficiarioRepo.save(newBeneficiario); }
  @PutMapping("/{id}") public ResponseEntity<Beneficiario> update(@PathVariable String id, @RequestBody Beneficiario updatedBeneficiario) { if (!beneficiarioRepo.existsById(id)) { return ResponseEntity.notFound().build(); } updatedBeneficiario.setId(id); return ResponseEntity.ok(beneficiarioRepo.save(updatedBeneficiario)); }

  // DELETE (Atualizado com lógica de cascata)
  @DeleteMapping("/{id}")
  @Transactional // Garante que ambas as operações funcionem como uma só
  public ResponseEntity<Void> delete(@PathVariable String id) {
    if (!beneficiarioRepo.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    // 1. Deleta as doações associadas ao beneficiário
    doacaoRepo.deleteByBeneficiarioId(id);
    // 2. Deleta o próprio beneficiário
    beneficiarioRepo.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}