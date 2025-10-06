package com.saudesolidaria.app.controller;

import com.saudesolidaria.app.model.Beneficiario;
import com.saudesolidaria.app.repo.BeneficiarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/beneficiarios")
public class BeneficiariosController {
  private final BeneficiarioRepository repo;

  public BeneficiariosController(BeneficiarioRepository repo) {
    this.repo = repo;
  }

  // READ (All)
  @GetMapping
  public List<Beneficiario> all() {
    return repo.findAll();
  }

  // READ (by ID)
  @GetMapping("/{id}")
  public ResponseEntity<Beneficiario> one(@PathVariable String id) {
    Optional<Beneficiario> optionalBeneficiario = repo.findById(id);
    if (optionalBeneficiario.isPresent()) {
      return ResponseEntity.ok(optionalBeneficiario.get());
    }
    return ResponseEntity.notFound().build();
  }

  // CREATE
  @PostMapping
  public Beneficiario create(@RequestBody Beneficiario newBeneficiario) {
    return repo.save(newBeneficiario);
  }

  // UPDATE
  @PutMapping("/{id}")
  public ResponseEntity<Beneficiario> update(@PathVariable String id, @RequestBody Beneficiario updatedBeneficiario) {
    if (!repo.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    updatedBeneficiario.setId(id); // Garante que o ID do objeto é o mesmo da URL
    return ResponseEntity.ok(repo.save(updatedBeneficiario));
  }

  // DELETE
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    if (!repo.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    repo.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}