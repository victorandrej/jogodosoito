package br.com.victorandrej.jogodosoito.componentes;

public class Peca {
	private int numero;
	private Posicao posicao;

	public Peca(int numero, Posicao posicaoInicial) {
		this.numero = numero;
		this.posicao = posicaoInicial;
	}

	public int getNumero() {
		return this.numero;
	}

	public Posicao getPosicao() {
		return this.posicao;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Peca))
			return false;

		return this.numero == ((Peca) obj).numero;
	}

	@Override
	public String toString() {
		return "Peca: " + numero;
	}

	void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}
}
