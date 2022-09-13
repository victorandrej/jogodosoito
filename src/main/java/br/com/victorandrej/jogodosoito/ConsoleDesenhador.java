package br.com.victorandrej.jogodosoito;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.victorandrej.jogodosoito.componentes.Peca;
import br.com.victorandrej.jogodosoito.componentes.Tabuleiro;
import br.com.victorandrej.jogodosoito.interfaces.Desenhador;

public class ConsoleDesenhador implements Desenhador {

	@Override
	public void desenhar(Tabuleiro tabuleiro) {
		List<Peca> ordenedPeca = Stream.of(tabuleiro.getPecas()).sorted((p1,p2)->p1.getPosicao().estaNaFrente(p2.getPosicao()) ? 1 : -1).collect(Collectors.toList());
		StringBuilder sb = new StringBuilder();
		int contador = 0;
		for(Peca p : ordenedPeca) {
			
			sb.append(p.getNumero()+" ");
			
			contador++;
			
			if(contador == tabuleiro.getLargura()) {
				contador = 0;
				sb.append("\n");
			}
		}
		
		System.out.print(sb+"\n");
	}

}
