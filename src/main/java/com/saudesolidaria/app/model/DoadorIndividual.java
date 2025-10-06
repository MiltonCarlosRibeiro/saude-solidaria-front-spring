package com.saudesolidaria.app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("doadores_individuais")
public class DoadorIndividual {
  @Id
  private String id;
  private String nome;
  private String email;
  private String telefone;
}
