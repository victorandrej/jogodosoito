package br.com.victorandrej.jogodosoito;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;

import br.com.victorandrej.jogodosoito.componentes.Peca;
import br.com.victorandrej.jogodosoito.componentes.Posicao;
import br.com.victorandrej.jogodosoito.componentes.Tabuleiro;
import br.com.victorandrej.jogodosoito.interfaces.Entrada;

public class ConsoleEntrada implements Entrada {
	Scanner scanner;

	public ConsoleEntrada() {
		this.scanner = new Scanner(System.in);
	}

	@Override
	public void entrar(Game game) {
		Map<Peca, Posicao[]> pecasComMovimentos = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		
		sb.append("Pecas selecionaveis: ");
		
		for (Peca peca : game.getTabuleiro().getPecas()) {
			Posicao[] posicoes =  game.getTabuleiro().posicoesDisponiveis(peca);
			
			if (posicoes.length != 0) {
				sb.append(peca);
				sb.append(" ");
				pecasComMovimentos.put(peca, posicoes);

			}
		}
		
		System.out.println(sb);
		System.out.print("selecione uma peca ou digite 'sair' para sair: ");
		
		String valor = scanner.nextLine();

		if (valor.equalsIgnoreCase("sair"))
			game.sair();

		Integer numPeca = Util.parseInt(valor);
		
		if (numPeca == null)
			return;

		Optional<Entry<Peca, Posicao[]>> entryPeca = pecasComMovimentos.entrySet().stream()
				.filter(f -> f.getKey().getNumero() == numPeca).findFirst();
		
		if (entryPeca.isPresent())
			movimentoMenu(entryPeca.get(),  game.getTabuleiro());
		else
			System.out.println("Numero invalido!");

	}

	private void movimentoMenu(Entry<Peca, Posicao[]> entryPeca, Tabuleiro tabuleiro) {
		Peca peca = entryPeca.getKey();
		Posicao[] posicoes = entryPeca.getValue();
		
		System.out.println(peca + " Movimentos: ");
		
		for (int i = 0; i < posicoes.length; i++) {
			System.out.println(i + 1 + ")" + posicoes[i]);
		}
		
		System.out.println("0) Voltar");
		System.out.print("Opcao: ");
		
		Integer valor = Util.parseInt(scanner.nextLine());

		if (valor == null || valor < 0 || valor > posicoes.length) {
			System.out.println("Opcao Invalida");
			
			movimentoMenu(entryPeca, tabuleiro);
			return;
		}

		if (valor == 0)
			return;

		tabuleiro.mudarPosicaoPeca(peca, posicoes[valor - 1]);
	}

}
