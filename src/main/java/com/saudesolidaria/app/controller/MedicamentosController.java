package com.saudesolidaria.app.controller;

import com.saudesolidaria.app.model.Medicamento;
import com.saudesolidaria.app.repo.MedicamentoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentosController {
  private final MedicamentoRepository repo;

  public MedicamentosController(MedicamentoRepository repo) {
    this.repo = repo;
  }

  // READ (All)
  @GetMapping
  public List<Medicamento> all() {
    return repo.findAll();
  }

  // READ (by ID)
  @GetMapping("/{id}")
  public ResponseEntity<Medicamento> one(@PathVariable String id) {
    return repo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  // CREATE
  @PostMapping
  public Medicamento create(@RequestBody Medicamento newMedicamento) {
    return repo.save(newMedicamento);
  }

  // UPDATE
  @PutMapping("/{id}")
  public ResponseEntity<Medicamento> update(@PathVariable String id, @RequestBody Medicamento updatedMedicamento) {
    if (!repo.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    updatedMedicamento.setId(id);
    return ResponseEntity.ok(repo.save(updatedMedicamento));
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