package com.saudesolidaria.app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field; // IMPORTAR

@Data
@Document("doacoes")
public class Doacao {
  @Id
  private String id;

  private String tipoDoador; // Mantido, pode ser útil

  @Field("doador_id") // Mapeando o campo do banco
  private String doadorId;

  @Field("beneficiario_id") // Mapeando o campo do banco
  private String beneficiarioId;

  @Field("medicamento_id") // Mapeando o campo do banco
  private String medicamentoId;

  private Integer quantidade;

  private String status; // Campo novo

  @Field("dataSolicitacao") // Mapeando o campo do banco
  private String dataSolicitacao;

  @Field("dataAprovacao") // Mapeando o campo do banco
  private String dataAprovacao;
}