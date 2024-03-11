package br.com.squad3.contrato.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.squad3.contrato.dto.FornecedorDto;
import br.com.squad3.contrato.service.FornecedorService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

	@Autowired
	private FornecedorService fornecedorService;
	
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml"})
	public ResponseEntity<FornecedorDto> findById(@PathVariable UUID id) {
		var fornecedor = fornecedorService.findById(id);
		return ResponseEntity.ok(fornecedor);
	}
	
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		fornecedorService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}", produces = { "application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
	@Transactional
	public ResponseEntity<FornecedorDto> update(@PathVariable UUID id, @RequestBody FornecedorDto fornecedorDto) {
		fornecedorDto = fornecedorService.update(id, fornecedorDto);
		return ResponseEntity.ok(fornecedorDto);
	}

	@PostMapping(produces = { "application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
	@Transactional
	public ResponseEntity<FornecedorDto> save(@RequestBody FornecedorDto fornecedorDto) {
		fornecedorDto = fornecedorService.save(fornecedorDto);
		return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(fornecedorDto);
	}
	
	@GetMapping()
	public ResponseEntity<Page<FornecedorDto>> findAll(
			@PageableDefault(size = 10, page = 0, sort = "descricao") Pageable pageable) {
		Page<FornecedorDto> fornecedorDtos = fornecedorService.findAll(pageable);
		return ResponseEntity.ok(fornecedorDtos);
	}

}
