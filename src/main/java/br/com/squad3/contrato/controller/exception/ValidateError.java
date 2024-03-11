package br.com.squad3.contrato.controller.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidateError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<ValidateMessage> mensagens = new ArrayList<ValidateMessage>();

	public void addMensagens(String campo, String mensagem) {
		mensagens.add(new ValidateMessage(campo, mensagem));
	}

}
