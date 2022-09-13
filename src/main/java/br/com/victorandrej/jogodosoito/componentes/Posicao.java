package br.com.victorandrej.jogodosoito.componentes;

public class Posicao {
	private int x;
	private int y;

	public Posicao(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public boolean estaNaFrente(Posicao posicao) {
		if (posicao == null)
			return false;

		if (this.y == posicao.y) {
			return this.x >= posicao.x;
		}

		
		return this.y > posicao.y;

	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Posicao))
			return false;

		return (this.x == ((Posicao) obj).x) && (this.y == ((Posicao) obj).y);

	}

	@Override
	public String toString() {
		return "X: " + this.x + " Y: " + y;
	}
}
