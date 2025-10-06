package com.saudesolidaria.app.repo;

import com.saudesolidaria.app.model.Doacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoRepository extends MongoRepository<Doacao, String> {
    // Novos métodos para exclusão em cascata
    void deleteByBeneficiarioId(String beneficiarioId);
    void deleteByDoadorId(String doadorId);
}