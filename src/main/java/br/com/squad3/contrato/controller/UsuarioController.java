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

import br.com.squad3.contrato.dto.UsuarioDto;
import br.com.squad3.contrato.service.UsuarioService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml"})
	public ResponseEntity<UsuarioDto> findById(@PathVariable UUID id) {
		var usuario = usuarioService.findById(id);
		return ResponseEntity.ok(usuario);
	}
	
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		usuarioService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}", produces = { "application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
	@Transactional
	public ResponseEntity<UsuarioDto> update(@PathVariable UUID id, @RequestBody UsuarioDto UsuarioDto) {
		UsuarioDto = usuarioService.update(id, UsuarioDto);
		return ResponseEntity.ok(UsuarioDto);
	}

	@PostMapping(produces = { "application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
	@Transactional
	public ResponseEntity<UsuarioDto> save(@RequestBody UsuarioDto UsuarioDto) {
		UsuarioDto = usuarioService.save(UsuarioDto);
		return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(UsuarioDto);
	}
	
	@GetMapping()
	public ResponseEntity<Page<UsuarioDto>> findAll(
			@PageableDefault(size = 10, page = 0, sort = "descricao") Pageable pageable) {
		Page<UsuarioDto> UsuarioDtos = usuarioService.findAll(pageable);
		return ResponseEntity.ok(UsuarioDtos);
	}

}
