package br.com.squad3.contrato.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.squad3.contrato.controller.exception.ControllerNotFoundException;
import br.com.squad3.contrato.dto.UsuarioDto;
import br.com.squad3.contrato.entities.Usuario;
import br.com.squad3.contrato.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	private UsuarioDto toDto(Usuario usuario) {
		return new UsuarioDto(
				usuario.getId(),
				usuario.getNome(),
				usuario.getCpf(),
				usuario.getEmail(),
				usuario.getTipoAcesso()
			);
	}

	private Usuario toEntity(UsuarioDto usuarioDto) {
		return new Usuario(
				usuarioDto.id(),
				usuarioDto.nome(),
				usuarioDto.cpf(),
				usuarioDto.email(),
				usuarioDto.tipoAcesso() 
			);
	}

	public UsuarioDto findById(UUID id) {
		var usuario = usuarioRepository.findById(id)
				.orElseThrow(() ->
				new ControllerNotFoundException("Usuario não encontrado"));
		return toDto(usuario);
	}
	
	public Page<UsuarioDto> findAll (Pageable pageable){
		Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
		return usuarios.map(this::toDto);
	}
	
	public UsuarioDto save(UsuarioDto usuarioDto) {
		var usuario = toEntity(usuarioDto);
		usuario = usuarioRepository.save(usuario);
		return toDto(usuario);
	}

	public UsuarioDto update(UUID id, UsuarioDto usuarioDto) {

		try {
			var buscaUsuario = usuarioRepository.getReferenceById(id);
			buscaUsuario.setNome(usuarioDto.nome());
			buscaUsuario.setCpf(usuarioDto.cpf());
			buscaUsuario.setEmail(usuarioDto.email());
			buscaUsuario.setTipoAcesso(usuarioDto.tipoAcesso());
			buscaUsuario = usuarioRepository.save(buscaUsuario);
			
			return toDto(buscaUsuario);
		} catch (EntityNotFoundException e) {
			throw new ControllerNotFoundException("Usuario não encontrado");
		}
	}

	public void delete(UUID id) {
		usuarioRepository.deleteById(id);
	}
	
}
