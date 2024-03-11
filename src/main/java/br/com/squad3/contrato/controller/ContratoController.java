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

import br.com.squad3.contrato.dto.ContratoDto;
import br.com.squad3.contrato.service.ContratoService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

	@Autowired
	private ContratoService contratoService;

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml"})
	public ResponseEntity<ContratoDto> findById(@PathVariable UUID id) {
		var contrato = contratoService.findById(id);
		return ResponseEntity.ok(contrato);
	}
	
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		contratoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}", produces = { "application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
	@Transactional
	public ResponseEntity<ContratoDto> update(@PathVariable UUID id, @RequestBody ContratoDto contratoDto) {
		contratoDto = contratoService.update(id, contratoDto);
		return ResponseEntity.ok(contratoDto);
	}

	@PostMapping(produces = { "application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
	@Transactional
	public ResponseEntity<ContratoDto> save(@RequestBody ContratoDto contratoDto) {
		contratoDto = contratoService.save(contratoDto);
		return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(contratoDto);
	}
	
	@GetMapping()
	public ResponseEntity<Page<ContratoDto>> findAll(
			@PageableDefault(size = 10, page = 0, sort = "descricao") Pageable pageable) {
		Page<ContratoDto> contratoDtos = contratoService.findAll(pageable);
		return ResponseEntity.ok(contratoDtos);
	}

}
