package com.schneide.abas.ccd.yellow.lsp.parts;

public class Wasserbehälter {

	private int füllstand;

	public Wasserbehälter() {
		this.füllstand = 0;
	}

	public void fülleMit(int wasser) {
		this.füllstand += wasser;
	}

	public int zapfe(int wasserMenge) {
		return Math.min(füllstand, wasserMenge);
	}
}
