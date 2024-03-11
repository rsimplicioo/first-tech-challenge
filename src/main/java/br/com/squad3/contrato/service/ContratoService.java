package br.com.squad3.contrato.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.squad3.contrato.controller.exception.ControllerNotFoundException;
import br.com.squad3.contrato.dto.ContratoDto;
import br.com.squad3.contrato.entities.Contrato;
import br.com.squad3.contrato.repository.ContratoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ContratoService {

	@Autowired
	private ContratoRepository contratoRepository;

	private ContratoDto toDto(Contrato contrato) {
		return new ContratoDto(
				contrato.getId(),
				contrato.getTitulo(),
				contrato.getDescricao(),
				contrato.getResposavel(),
				contrato.getFornecedor(),
				contrato.getAssinaturaResponsavel(),
				contrato.getTipoContrato(),
				contrato.getDateIncio(),
				contrato.getDateFinal() 
			);
	}

	private Contrato toEntity(ContratoDto contratoDto) {
		return new Contrato(
				contratoDto.id(),
				contratoDto.titulo(),
				contratoDto.descricao(),
				contratoDto.responsavel(),
				contratoDto.fornecedor(),
				contratoDto.assinaturaResponsavel(),
				contratoDto.tipoContrato(),
				contratoDto.dateIncio(),
				contratoDto.dateFinal() 
			);
	}

	public ContratoDto findById(UUID id) {
		var contrato = contratoRepository.findById(id)
				.orElseThrow(() ->
				new ControllerNotFoundException("Contrato não encontrado"));
		return toDto(contrato);
	}
	
	public Page<ContratoDto> findAll (Pageable pageable){
		Page<Contrato> contratos = contratoRepository.findAll(pageable);
		return contratos.map(this::toDto);
	}
	
	public ContratoDto save(ContratoDto contratoDto) {
		var contrato = toEntity(contratoDto);
		contrato = contratoRepository.save(contrato);
		return toDto(contrato);
	}

	public ContratoDto update(UUID id, ContratoDto contratoDto) {

		try {
			var buscaContrato = contratoRepository.getReferenceById(id);
			buscaContrato.setDescricao(contratoDto.descricao());
			buscaContrato.setResposavel(contratoDto.responsavel());
			buscaContrato.setAssinaturaResponsavel(contratoDto.assinaturaResponsavel());
			buscaContrato.setDateIncio(contratoDto.dateIncio());
			buscaContrato.setDateFinal(contratoDto.dateFinal());
			buscaContrato = contratoRepository.save(buscaContrato);
			
			return toDto(buscaContrato);
		} catch (EntityNotFoundException e) {
			throw new ControllerNotFoundException("Contrato não encontrado");
		}
	}

	public void delete(UUID id) {
		contratoRepository.deleteById(id);
	}
	
}
