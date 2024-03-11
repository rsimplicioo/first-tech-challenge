package br.com.squad3.contrato.entities;

import java.util.UUID;

import br.com.squad3.contrato.dto.PerfilAcesso;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String nome;
	private Long cpf;
	private String email;
	private PerfilAcesso tipoAcesso;

}
