package com.saudesolidaria.app.repo;

import com.saudesolidaria.app.model.Beneficiario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BeneficiarioRepository extends MongoRepository<Beneficiario, String> {
}