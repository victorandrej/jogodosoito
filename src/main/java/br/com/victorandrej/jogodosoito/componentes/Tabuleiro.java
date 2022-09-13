package br.com.victorandrej.jogodosoito.componentes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.victorandrej.jogodosoito.exceptions.MuitasPecasException;
import br.com.victorandrej.jogodosoito.exceptions.PecaNaoRegistradaException;
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
		if (this.posicaoEmUso(posicao))
			throw new PosicaoInvalidaException("Nova posicao: " + posicao + " da Peca: " + peca + "ja usada");

		if (this.isPosicaoForaDoTabuleiro(posicao))
			throw new PosicaoInvalidaException("Posicao: " + posicao + " fora dos limites do tabuleiro");

		peca.setPosicao(posicao);

	}

	public void adicionarPeca(Peca peca) {
		if (pecas.size() == (altura * largura) - 1)
			throw new MuitasPecasException("deve haver pelo menos um espaco no tabuleiro");

		if (this.posicaoEmUso(peca.getPosicao()))
			throw new PosicaoInvalidaException(
					"Posicao: " + peca.getPosicao().toString() + " da Peca: " + peca + "ja usada");

		pecas.add(peca);
	}

	private boolean isPosicaoForaDoTabuleiro(Posicao posicao) {
		return posicao.getX() > largura || posicao.getX() < 0 || posicao.getY() > altura || posicao.getY() < 0;
	}

	private boolean posicaoEmUso(Posicao posicao) {
		return pecas.stream().anyMatch(p -> p.getPosicao().equals(posicao));
	}

	private boolean posicaoValida(Posicao posicao) {
		return !posicaoEmUso(posicao) && !isPosicaoForaDoTabuleiro(posicao);
	}

	private boolean ehAlcancavel(Posicao posicaoAtual, Posicao posicaoDesejada) {
		if (posicaoAtual.equals(posicaoDesejada))
			return true;
		
		if(ehAlcancavel(posicaoAtual, posicaoDesejada,-1,0))
			return true;

		if(ehAlcancavel(posicaoAtual, posicaoDesejada,1,0))
			return true;

		if(ehAlcancavel(posicaoAtual, posicaoDesejada,0,-1))
			return true;

		if(ehAlcancavel(posicaoAtual, posicaoDesejada,0,1))
			return true;

		return false;
	}

	private boolean ehAlcancavel(Posicao posicaoAtual, Posicao posicaoDesejada,int incrementoY,int incrementoX) {
		try {
			Posicao posicao = new Posicao(posicaoAtual.getX()+incrementoX, posicaoAtual.getY()+ incrementoY);
			if (!posicaoValida(posicao))
				return false;
			
			return ehAlcancavel(posicao, posicaoDesejada);
		} catch (StackOverflowError err) {
			//isso ocorre quando a verificaçao entra em loop infinito indo para os mesmo valores sempre
			return false;
		}
	}

	public Posicao[] posicoesDisponiveis(Peca peca) {
		if (!this.pecas.contains(peca))
			throw new PecaNaoRegistradaException(peca + " não registrada");

		List<Posicao> posicoes = new ArrayList<>();

		for (int i = 0; i < this.altura; i++) {
			for (int j = 0; j < this.largura; j++) {
				Posicao posicao = new Posicao(j, i);
				if (posicaoValida(posicao) && ehAlcancavel(peca.getPosicao(), posicao) && !posicoes.contains(posicao))
					posicoes.add(posicao);
			}
		}

		return posicoes.toArray(new Posicao[0]);
	}

}
