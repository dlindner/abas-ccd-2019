package com.schneide.abas.ccd.yellow.lsp.domain;

public class Rezept {

	private int kaffee;
	private int wasser;

	public Rezept(int kaffee, int wasser) {
		super();
		this.kaffee = kaffee;
		this.wasser = wasser;
	}

	public int kaffeeMenge() {
		return kaffee;
	}

	public int wasserMenge() {
		return wasser;
	}
}
