package br.com.victorandrej.jogodosoito;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import br.com.victorandrej.jogodosoito.componentes.Peca;
import br.com.victorandrej.jogodosoito.componentes.Posicao;
import br.com.victorandrej.jogodosoito.componentes.Tabuleiro;
import br.com.victorandrej.jogodosoito.interfaces.Entrada;

public class ConsoleEntrada implements Entrada {
	Scanner scanner ;
	public ConsoleEntrada() {
		this.scanner =  new Scanner(System.in);
	}
	@Override
	public void entrar(Tabuleiro tabuleiro) {
		Map<Peca, Posicao[]> pecasComMovimentos = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		for (Peca peca : tabuleiro.getPecas()) {
			Posicao[] posicoes = tabuleiro.posicoesDisponiveis(peca);
			if (posicoes.length != 0) {
				sb.append(peca);
				sb.append(" ");
				pecasComMovimentos.put(peca, posicoes);

			}
		}
		System.out.println(sb);
		System.out.print("selecione uma peca: ");
		String valor = scanner.nextLine();
		if(valor.equals("sair"))
			System.exit(0);

	}

}
