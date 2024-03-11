package br.com.squad3.contrato.dto;

import java.util.UUID;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.NotBlank;

public record FornecedorDto(	
		UUID id,
		@NotBlank(message = "Nome não pode ser branco")
		String nomeFantasia,
		@CNPJ(message = "Cnpj invalido")
		String cnpj
		) {

}
