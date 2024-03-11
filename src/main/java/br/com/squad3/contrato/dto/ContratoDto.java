package br.com.squad3.contrato.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record ContratoDto(
		UUID id, 
		String titulo,
		String descricao,
		@NotBlank(message = "Responsavel não pode esta vazio")
		String responsavel,
		@NotBlank(message = "Fornecedor não pode esta vazio ")
		String fornecedor,
		@NotBlank(message = "Assinatura não pode esta vazia ")
		String assinaturaResponsavel,
		String tipoContrato,
		String dateIncio,
		String dateFinal) {
}
