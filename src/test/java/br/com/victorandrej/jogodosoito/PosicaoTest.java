package br.com.victorandrej.jogodosoito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import br.com.victorandrej.jogodosoito.componentes.Posicao;

public class PosicaoTest {

	@Test
	public void posicaoEstaNaFrente() {
		Posicao posicaoAtras = new Posicao(0, 0);
		Posicao posicaoFrente = new Posicao(0, 2);
		assertTrue(posicaoFrente.estaNaFrente(posicaoAtras));
	}
	
	@Test
	public void posicaoEstaAtras() {
		Posicao posicaoAtras = new Posicao(0, 0);
		Posicao posicaoFrente = new Posicao(1, 0);
		assertFalse(posicaoAtras.estaNaFrente(posicaoFrente));
	}
}
