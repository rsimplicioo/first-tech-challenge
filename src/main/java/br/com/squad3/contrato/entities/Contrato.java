package br.com.squad3.contrato.entities;

import java.util.UUID;

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
public class Contrato {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String titulo;
	private String descricao;
	private String resposavel;
	private String fornecedor;
	private String AssinaturaResponsavel;
	String tipoContrato;
	private String dateIncio;
	private String dateFinal;

}
