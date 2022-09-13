package br.com.victorandrej.jogodosoito.exceptions;

public class PosicaoInvalidaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PosicaoInvalidaException(String message) {
		super(message);
	}
}
