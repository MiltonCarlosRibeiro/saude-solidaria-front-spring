package com.saudesolidaria.app.repo;

import com.saudesolidaria.app.model.Medicamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicamentoRepository extends MongoRepository<Medicamento, String> {
}