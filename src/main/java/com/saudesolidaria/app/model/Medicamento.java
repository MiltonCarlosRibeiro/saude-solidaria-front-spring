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
  private String fabricante;
  private String lote;
  private String validade;
}
