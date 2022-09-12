package br.com.victorandrej.jogodosoito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.victorandrej.jogodosoito.componentes.Peca;
import br.com.victorandrej.jogodosoito.componentes.Tabuleiro;

public class GameTest {
 
	Game game;
	
	@BeforeEach
	public void initGame() {
		game = new Game();
	}
	@Test
	public void consegueGerarPecasComPosicoesDiferentes() {
		int AlturaELargura = 3;
		List<Peca> pecas = game.gerarPecas(new Tabuleiro(AlturaELargura,AlturaELargura,))
	}
}
