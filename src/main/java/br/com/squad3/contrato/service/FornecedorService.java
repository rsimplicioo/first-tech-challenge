package br.com.squad3.contrato.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.squad3.contrato.controller.exception.ControllerNotFoundException;
import br.com.squad3.contrato.dto.FornecedorDto;
import br.com.squad3.contrato.entities.Fornecedor;
import br.com.squad3.contrato.repository.FornecedorRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	private FornecedorDto toDto(Fornecedor fornecedor) {
		return new FornecedorDto(
				fornecedor.getId(),
				fornecedor.getNomeFantasia(),
				fornecedor.getCnpj()
			);
	}

	private Fornecedor toEntity(FornecedorDto fornecedorDto) {
		return new Fornecedor(
				fornecedorDto.id(),
				fornecedorDto.nomeFantasia(),
				fornecedorDto.cnpj()
			);
	}

	public FornecedorDto findById(UUID id) {
		var fornecedor = fornecedorRepository.findById(id)
				.orElseThrow(() ->
				new ControllerNotFoundException("Fornecedor não encontrado"));
		return toDto(fornecedor);
	}
	
	public Page<FornecedorDto> findAll (Pageable pageable){
		Page<Fornecedor> fornecedor = fornecedorRepository.findAll(pageable);
		return fornecedor.map(this::toDto);
	}
	
	public FornecedorDto save(FornecedorDto fornecedorDto) {
		var fornecedor = toEntity(fornecedorDto);
		fornecedor = fornecedorRepository.save(fornecedor);
		return toDto(fornecedor);
	}

	public FornecedorDto update(UUID id, FornecedorDto fornecedorDto) {

		try {
			var buscaFornecedor = fornecedorRepository.getReferenceById(id);
			buscaFornecedor.setNomeFantasia(fornecedorDto.nomeFantasia());
			buscaFornecedor.setCnpj(fornecedorDto.cnpj());
			buscaFornecedor = fornecedorRepository.save(buscaFornecedor);
			
			return toDto(buscaFornecedor);
		} catch (EntityNotFoundException e) {
			throw new ControllerNotFoundException("Fornecedor não encontrado");
		}
	}

	public void delete(UUID id) {
		fornecedorRepository.deleteById(id);
	}
	
}
