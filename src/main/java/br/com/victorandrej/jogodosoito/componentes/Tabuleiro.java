package br.com.victorandrej.jogodosoito.componentes;

import java.util.HashSet;
import java.util.Set;

import br.com.victorandrej.jogodosoito.exceptions.MuitasPecasException;
import br.com.victorandrej.jogodosoito.exceptions.PosicaoInvalidaException;

public class Tabuleiro {
	private final int altura;
	private final int largura;
	private Set<Peca> pecas;

	public Tabuleiro(int altura, int largura) {
		this.altura = altura;
		this.largura = largura;
		this.pecas = new HashSet<Peca>();
	}

	public int getAltura() {
		return this.altura;
	}

	public int getLargura() {
		return this.largura;
	}

	public Peca[] getPecas() {
		return this.pecas.toArray(new Peca[0]);
	}

	public void mudarPosicaoPeca(Peca peca, Posicao posicao) {
		if(this.posicaoEmUso(peca))
			throw new PosicaoInvalidaException(
					"Posicao: " + peca.getPosicao().toString() + " da Peca: " + peca + "ja usada");
		
		if (this.isPosicaoForaDoTabuleiro(posicao))
			throw new PosicaoInvalidaException("posicao fora dos limites do tabuleiro");

		peca.setPosicao(posicao);

	}

	public void adicionarPeca(Peca peca) {
		if (pecas.size() == (altura * largura) - 1)
			throw new MuitasPecasException("deve haver pelo menos um espaco no tabuleiro");
		
		if (this.posicaoEmUso(peca))
			throw new PosicaoInvalidaException(
					"Posicao: " + peca.getPosicao().toString() + " da Peca: " + peca + "ja usada");
		
		pecas.add(peca);
	}

	private boolean isPosicaoForaDoTabuleiro(Posicao posicao) {
		return posicao.getX() > largura || posicao.getX() < 0 || posicao.getY() > altura || posicao.getY() < 0;
	}

	private boolean posicaoEmUso(Peca peca) {
		return pecas.stream().anyMatch(p -> p != peca && p.getPosicao().equals(peca.getPosicao()));
	}

}
