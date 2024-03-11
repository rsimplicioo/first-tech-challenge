package br.com.squad3.contrato.controller.exception;

public class ControllerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ControllerNotFoundException(String exception) {
		super(exception);
	}
}
