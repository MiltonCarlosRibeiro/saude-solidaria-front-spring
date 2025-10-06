package com.saudesolidaria.app.controller;

import com.saudesolidaria.app.model.Doacao;
import com.saudesolidaria.app.repo.DoacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doacoes")
public class DoacoesController {
  private final DoacaoRepository repo;

  public DoacoesController(DoacaoRepository repo) {
    this.repo = repo;
  }

  // READ (All)
  @GetMapping
  public List<Doacao> all() {
    return repo.findAll();
  }

  // READ (by ID)
  @GetMapping("/{id}")
  public ResponseEntity<Doacao> one(@PathVariable String id) {
    return repo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  // CREATE
  @PostMapping
  public Doacao create(@RequestBody Doacao newDoacao) {
    return repo.save(newDoacao);
  }

  // UPDATE
  @PutMapping("/{id}")
  public ResponseEntity<Doacao> update(@PathVariable String id, @RequestBody Doacao updatedDoacao) {
    if (!repo.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    updatedDoacao.setId(id);
    return ResponseEntity.ok(repo.save(updatedDoacao));
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