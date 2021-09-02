package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroExceprion;

public class Calculadora {

	public int somar(int a, int b) {
		return a + b;
	}

	public int subtrair(int a, int b) {
		return a - b;
	}

	public int dividir(int a, int b) throws NaoPodeDividirPorZeroExceprion {
		if(b == 0) {
			throw new NaoPodeDividirPorZeroExceprion();
		}
		return a / b;
	}

}
