package br.com.squad3.contrato.dto;

import java.util.UUID;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(	
		UUID id,
		@NotBlank(message = "Nome não pode ser branco")
		String nome,
		@CPF(message = "Cpf invalido")
		Long cpf,
		@Email(message = "Email invalido")
		String email,
		
		PerfilAcesso tipoAcesso
		) {

}
