package com.saudesolidaria.app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("beneficiarios")
public class Beneficiario {
  @Id
  private String id;
  private String nome;
  private String documento;
  private String endereco;
  private String telefone;
}
