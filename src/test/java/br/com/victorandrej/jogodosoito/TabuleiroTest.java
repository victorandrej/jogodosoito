package br.com.victorandrej.jogodosoito;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.victorandrej.jogodosoito.componentes.Peca;
import br.com.victorandrej.jogodosoito.componentes.Posicao;
import br.com.victorandrej.jogodosoito.componentes.Tabuleiro;
import br.com.victorandrej.jogodosoito.exceptions.MuitasPecasException;
import br.com.victorandrej.jogodosoito.exceptions.PosicaoInvalidaException;


public class TabuleiroTest {

	
	@Test
	public void adicionarMaisPecasQueOTabuleiroSuporta() {
		int tabuleiroTamanho = 2;
		Tabuleiro tabuleiro = new Tabuleiro(tabuleiroTamanho, tabuleiroTamanho);
		
		assertThrows(MuitasPecasException.class, ()->{
			for(int i = 1; i <= tabuleiroTamanho;i++)	
				for(int j = 1; j <= tabuleiroTamanho;j++)	
					tabuleiro.adicionarPeca(new Peca(i+j, new Posicao(j,i)));

		});
	}
	
	@Test
	public void adicionarQuantidadeDePecasQueOTabuleiroSuporta() {
		int tabuleiroTamanho = 2;
		Tabuleiro tabuleiro = new Tabuleiro(tabuleiroTamanho, tabuleiroTamanho);
		
		assertDoesNotThrow(()->{
			for(int i = 1; i <= tabuleiroTamanho;i++)	
				for(int j = 1; j < tabuleiroTamanho;j++)	
					tabuleiro.adicionarPeca(new Peca(i+j, new Posicao(j,i)));

		});
	}
	@Test
	public void adicionarPecaNoMesmoLugarQueOutraPeca() {
		int tabuleiroTamanho = 2;
		Tabuleiro tabuleiro = new Tabuleiro(tabuleiroTamanho, tabuleiroTamanho);

		assertThrows(PosicaoInvalidaException.class, ()->{
			tabuleiro.adicionarPeca(new Peca(1, new Posicao(1,1)));
			tabuleiro.adicionarPeca(new Peca(2, new Posicao(1,1)));
		});
	}
	@Test
	public void adicionarPecaEmLugarNaoUsado() {
		int tabuleiroTamanho = 2;
		Tabuleiro tabuleiro = new Tabuleiro(tabuleiroTamanho, tabuleiroTamanho);
		
		assertDoesNotThrow(()->{
			tabuleiro.adicionarPeca(new Peca(1, new Posicao(1,1)));
			tabuleiro.adicionarPeca(new Peca(2, new Posicao(1,2)));
		});
	}
	@Test
	public void mudarPecaParaPosicaoInvalida() {
		assertThrows(PosicaoInvalidaException.class,()->{
			int tabuleiroTamanho = 2;
			Tabuleiro tabuleiro = new Tabuleiro(tabuleiroTamanho,tabuleiroTamanho);
			Peca peca = new Peca(1,new Posicao(1,1));
			tabuleiro.adicionarPeca(peca);
		
			tabuleiro.mudarPosicaoPeca(peca, new Posicao(-1, -1));
			
		});
		
	}
	@Test
	public void mudarPecaParaPosicaoValida() {
		assertDoesNotThrow(()->{
			int tabuleiroTamanho = 2;
			Tabuleiro tabuleiro = new Tabuleiro(tabuleiroTamanho,tabuleiroTamanho);
			Peca peca = new Peca(1,new Posicao(1,1));
			tabuleiro.adicionarPeca(peca);
			
			tabuleiro.mudarPosicaoPeca(peca, new Posicao(1, 1));
			
		});
		
	}
}
