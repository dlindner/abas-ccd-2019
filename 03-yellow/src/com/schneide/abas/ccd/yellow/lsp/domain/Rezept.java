package com.schneide.abas.ccd.yellow.lsp.domain;

import java.util.function.Function;

public class Rezept {

	private final int kaffee;
	private final int wasser;
	private final Function<Integer, Kaffeegetränk> ergebnis;

	public Rezept(
			int kaffee,
			int wasser,
			Function<Integer, Kaffeegetränk> ergebnis) {
		super();
		this.kaffee = kaffee;
		this.wasser = wasser;
		this.ergebnis = ergebnis;
	}

	public int kaffeeMenge() {
		return kaffee;
	}

	public int wasserMenge() {
		return wasser;
	}

	public Kaffeegetränk wendeAnAuf(int menge) {
		return this.ergebnis.apply(menge);
	}
}
