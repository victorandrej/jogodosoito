package br.com.victorandrej.jogodosoito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.victorandrej.jogodosoito.componentes.Tabuleiro;

public class GameTest {

	Game game;

	@BeforeEach
	public void initGame() {
		game = new Game(new Tabuleiro(3, 3), 7, new ConsoleDesenhador(), new ConsoleEntrada());
		game.quandoGanharListener(() -> {
			System.out.println("voce Ganhou!!");
			game.sair();
		});
	}

	@Test
	void gameStart() {
		game.start();
	}

}
