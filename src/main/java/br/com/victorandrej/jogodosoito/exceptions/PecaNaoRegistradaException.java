package br.com.victorandrej.jogodosoito.exceptions;

public class PecaNaoRegistradaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PecaNaoRegistradaException (String message) {
		super(message);
	}
}
