package br.com.victorandrej.jogodosoito;

import java.util.Optional;
import java.util.stream.Stream;

import br.com.victorandrej.jogodosoito.componentes.Peca;
import br.com.victorandrej.jogodosoito.componentes.Posicao;
import br.com.victorandrej.jogodosoito.componentes.Tabuleiro;
import br.com.victorandrej.jogodosoito.interfaces.Desenhador;

public class ConsoleDesenhador implements Desenhador {

	@Override
	public void desenhar(Tabuleiro tabuleiro) {
		StringBuilder sb = new StringBuilder();
		int contador = 0;
		int maiorTexto = Stream.of(tabuleiro.getPecas()).map(p -> p.toString().length()).reduce(0,
				(i, j) -> i > j ? i : j);

		for (int j = 0; j < tabuleiro.getAltura(); j++) {
			for (int i = 0; i < tabuleiro.getLargura(); i++) {
				Posicao posicao = new Posicao(i, j);
				Optional<Peca> peca = Stream.of(tabuleiro.getPecas()).filter(p -> p.getPosicao().equals(posicao))
						.findFirst();

				if (peca.isPresent()) {
					sb.append(criarTexto(peca.get().toString(),maiorTexto));
					
					
				} else {
					sb.append(criarTexto("VAZIO",maiorTexto));
				}
				sb.append(" ");
				contador++;

				if (contador >= tabuleiro.getLargura()) {
					sb.append("\n");
					contador = 0;
				}

			}
		}
		System.out.print(sb + "\n");
	}

	private String criarTexto(String texto, int tamanho) {
		tamanho = (tamanho - texto.length());
		if (tamanho % 2 != 0)
			tamanho++;
		String espacoEmBranco = new String(new char[tamanho / 2]).replace("\0", " ");
		return espacoEmBranco + texto + espacoEmBranco;
	}


}
