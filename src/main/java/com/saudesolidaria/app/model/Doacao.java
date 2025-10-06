package com.saudesolidaria.app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("doacoes")
public class Doacao {
  @Id
  private String id;
  private String tipoDoador;
  private String doadorId;
  private String beneficiarioId;
  private String medicamentoId;
  private Integer quantidade;
  private String data;
}
