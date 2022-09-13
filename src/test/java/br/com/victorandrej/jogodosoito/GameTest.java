package br.com.victorandrej.jogodosoito;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.victorandrej.jogodosoito.componentes.Peca;
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
