package com.saudesolidaria.app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("doadores_corporativos")
public class DoadorCorporativo {
  @Id
  private String id;
  private String razaoSocial;
  private String nomeFantasia; // Adicionado
  private String cnpj;
  private String statusConta;  // Adicionado
  // O campo "contato" foi removido para alinhar com o banco de dados
}