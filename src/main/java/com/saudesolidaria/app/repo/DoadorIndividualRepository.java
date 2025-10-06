package com.saudesolidaria.app.repo;

import com.saudesolidaria.app.model.DoadorIndividual;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoadorIndividualRepository extends MongoRepository<DoadorIndividual, String> {
}