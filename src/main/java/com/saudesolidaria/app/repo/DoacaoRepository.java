package com.saudesolidaria.app.repo;

import com.saudesolidaria.app.model.Doacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoacaoRepository extends MongoRepository<Doacao, String> {
}