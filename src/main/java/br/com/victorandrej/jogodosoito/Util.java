package br.com.victorandrej.jogodosoito;

public class Util {

	public static Integer parseInt(String valor) {
		try {
			return Integer.parseInt(valor);
		} catch (NumberFormatException err) {
			return null;
		}
	}

}
