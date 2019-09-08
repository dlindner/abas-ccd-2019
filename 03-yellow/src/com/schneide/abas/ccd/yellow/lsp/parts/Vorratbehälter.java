package com.schneide.abas.ccd.yellow.lsp.parts;

import com.schneide.abas.ccd.yellow.lsp.domain.Füllung;

public class Vorratbehälter<F extends Füllung> {

	private int füllstand;

	public Vorratbehälter() {
		super();
		this.füllstand = 0;
	}

	public void fülleMit(F füllung) {
		this.füllstand += füllung.menge();
	}

	public int hole(int kaffeeMenge) {
		return Math.min(füllstand, kaffeeMenge);
	}
}
