package br.com.victorandrej.jogodosoito.componentes;

import java.util.HashSet;
import java.util.Set;

import br.com.victorandrej.jogodosoito.exceptions.MuitasPecasException;
import br.com.victorandrej.jogodosoito.exceptions.PosicaoJaUsadaException;

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

	public void adicionarPeca(Peca peca) {
		if (pecas.size() == (altura * largura) - 1)
			throw new MuitasPecasException("deve haver pelo menos um espaco no tabuleiro");

		if (pecas.stream().anyMatch(p -> p.getPosicao().equals(peca.getPosicao()))) {
			throw new PosicaoJaUsadaException(
					"Posicao: " + peca.getPosicao().toString() + " da Peca: " + peca + "ja usada");
		}
		
		pecas.add(peca);
	}

}
