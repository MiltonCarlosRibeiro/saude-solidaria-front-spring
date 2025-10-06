package com.saudesolidaria.app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("medicamentos")
public class Medicamento {
  @Id
  private String id;
  private String nome;
  private String principioAtivo; // Adicionado
  private String status;         // Adicionado
  private Integer quantidade;      // Adicionado
  private Object doador;         // Adicionado (representa o objeto aninhado)
  // Os campos "fabricante", "lote" e "validade" foram removidos
}