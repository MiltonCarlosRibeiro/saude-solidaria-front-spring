package com.saudesolidaria.app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("beneficiarios")
public class Beneficiario {
  @Id
  private String id;
  private String nomeCompleto; // Alterado de "nome"
  private String cpf;          // Alterado de "documento"
  private String email;        // Adicionado
  private String endereco;
  private String telefone;
}