package com.saudesolidaria.app.repo;

import com.saudesolidaria.app.model.DoadorCorporativo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoadorCorporativoRepository extends MongoRepository<DoadorCorporativo, String> {
}