package br.com.victorandrej.jogodosoito;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.victorandrej.jogodosoito.componentes.Tabuleiro;

public class GameTest {
 
	Game game;
	
	@BeforeEach
	public void initGame() {
		game = new Game(new Tabuleiro(3, 3),1,new ConsoleDesenhador(),(tabuleiro)->{
			Scanner scanner = new Scanner(System.in);
			if(scanner.nextLine().equals("sair"))
				System.exit(0);
		});
	}
	
	@Test
	void gameStart(){
		game.start();
	}

}
