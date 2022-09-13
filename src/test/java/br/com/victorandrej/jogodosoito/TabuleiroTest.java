package br.com.victorandrej.jogodosoito;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.victorandrej.jogodosoito.componentes.Peca;
import br.com.victorandrej.jogodosoito.componentes.Posicao;
import br.com.victorandrej.jogodosoito.componentes.Tabuleiro;
import br.com.victorandrej.jogodosoito.exceptions.MuitasPecasException;
import br.com.victorandrej.jogodosoito.exceptions.PecaNaoRegistradaException;
import br.com.victorandrej.jogodosoito.exceptions.PosicaoInvalidaException;

public class TabuleiroTest {

	int tabuleiroTamanho = 3;
	Tabuleiro tabuleiro;

	@BeforeEach
	public void initTabuleiro() {

		tabuleiro = new Tabuleiro(tabuleiroTamanho, tabuleiroTamanho);
	}

	@Test
	public void adicionarMaisPecasQueOTabuleiroSuporta() {

		assertThrows(MuitasPecasException.class, () -> {
			for (int i = 1; i <= tabuleiroTamanho; i++)
				for (int j = 1; j <= tabuleiroTamanho; j++)
					tabuleiro.adicionarPeca(new Peca(i + j, new Posicao(j, i)));

		});
	}

	@Test
	public void adicionarQuantidadeDePecasQueOTabuleiroSuporta() {

		assertDoesNotThrow(() -> {
			for (int i = 1; i <= tabuleiroTamanho; i++)
				for (int j = 1; j < tabuleiroTamanho; j++)
					tabuleiro.adicionarPeca(new Peca(i + j, new Posicao(j, i)));

		});
	}

	@Test
	public void adicionarPecaNoMesmoLugarQueOutraPeca() {

		assertThrows(PosicaoInvalidaException.class, () -> {
			tabuleiro.adicionarPeca(new Peca(1, new Posicao(1, 1)));
			tabuleiro.adicionarPeca(new Peca(2, new Posicao(1, 1)));
		});
	}

	@Test
	public void adicionarPecaEmLugarNaoUsado() {

		assertDoesNotThrow(() -> {
			tabuleiro.adicionarPeca(new Peca(1, new Posicao(1, 1)));
			tabuleiro.adicionarPeca(new Peca(2, new Posicao(1, 2)));
		});
	}

	@Test
	public void mudarPecaParaPosicaoInvalida() {
		assertThrows(PosicaoInvalidaException.class, () -> {
			Peca peca = new Peca(1, new Posicao(1, 1));
			tabuleiro.adicionarPeca(peca);

			tabuleiro.mudarPosicaoPeca(peca, new Posicao(-1, -1));

		});

	}

	@Test
	public void mudarPecaParaPosicaoValida() {
		assertDoesNotThrow(() -> {

			Peca peca = new Peca(1, new Posicao(1, 1));
			tabuleiro.adicionarPeca(peca);
			tabuleiro.mudarPosicaoPeca(peca, new Posicao(1, 2));

		});

	}

	@Test
	public void pegarPosicoesPossiveisDeUmaPecaNaoRegistrada() {
		assertThrows(PecaNaoRegistradaException.class, () -> {
			tabuleiro.posicoesDisponiveis(new Peca(1, new Posicao(1, 1)));

		});
	}
	
	@Test
	public void pegarTodasAsPosicoesPossiveis() {
		Peca peca = new Peca(1, new Posicao(1, 0));
		tabuleiro.adicionarPeca(peca);
		for(Posicao p : tabuleiro.posicoesDisponiveis(peca))
		System.out.println(p);
		assertEquals(8,tabuleiro.posicoesDisponiveis(peca).length);
	}
	@Test
	public void pegarTodasAsPosicoesPossiveisComPecasBloquandoTodosOsLados() {
		Peca peca = new Peca(1,new Posicao(0,0));
		Peca peca2 = new Peca(2,new Posicao(0,1));
		Peca peca3 = new Peca(3,new Posicao(1,0));
		tabuleiro.adicionarPeca(peca);
		tabuleiro.adicionarPeca(peca2);
		tabuleiro.adicionarPeca(peca3);

		assertEquals(0,tabuleiro.posicoesDisponiveis(peca).length);
	}
	@Test
	public void pegarTodasAsPosicoesPossiveisComUmaPecaNaFrente() {
		Peca peca = new Peca(1,new Posicao(1,1));
		Peca peca2 = new Peca(2,new Posicao(1,2));
		tabuleiro.adicionarPeca(peca);
		tabuleiro.adicionarPeca(peca2);
		assertEquals(7,tabuleiro.posicoesDisponiveis(peca).length);
	}
}
