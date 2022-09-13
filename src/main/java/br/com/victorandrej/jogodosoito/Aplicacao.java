package br.com.victorandrej.jogodosoito;

import java.util.Scanner;

import br.com.victorandrej.jogodosoito.componentes.Tabuleiro;

public class Aplicacao {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
		int tamanhoTabuleiro = tamanhoTabela();
		int quantidadeEspacos = quantidadeDeEspacos();
		Game game = new Game(new Tabuleiro(tamanhoTabuleiro, tamanhoTabuleiro), quantidadeEspacos, new ConsoleDesenhador(), new ConsoleEntrada());
		game.quandoGanharListener(()->{
			System.out.print("Parabens voce ganhou!! deseja continuar? Sim : s | Nao : qualquer valor  : ");
			if(scanner.nextLine().equalsIgnoreCase("s")) {
				game.resetar();
				game.start();
			}else
				game.sair();
			
		});
		game.start();
	}
	


	private static int tamanhoTabela() {
		System.out.print("Informe o tamanho do tabuleiro: (Padrao 3 '3x3'): ");
		String retorno = scanner.nextLine();

		if (retorno.isBlank())
			return 3;

		Integer valor = Util.parseInt(retorno);

		if (valor == null) {
			System.out.println("Valor invalido");
			return tamanhoTabela();
		}

		return valor;
	}

	private static int quantidadeDeEspacos() {
		System.out.print("Informe a quantidade de espacos no tabuleiro: (Padrao 1): ");
		String retorno = scanner.nextLine();

		if (retorno.isBlank())
			return 1;
		
		Integer valor = Util.parseInt(retorno);
		
		if (valor == null) {
			System.out.println("Valor invalido");
			return quantidadeDeEspacos();
		}

		return valor;
	}
}
