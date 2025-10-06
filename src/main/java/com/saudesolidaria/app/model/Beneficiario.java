package com.saudesolidaria.app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("beneficiarios")
public class Beneficiario {
  @Id
  private String id;
  private String nomeCompleto;
  private String cpf;
  private String email;
  private String endereco;
  private String telefone;
  private String medicamentoSolicitado; // NOVO CAMPO ADICIONADO
}