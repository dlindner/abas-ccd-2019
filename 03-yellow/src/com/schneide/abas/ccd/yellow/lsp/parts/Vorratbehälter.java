package com.schneide.abas.ccd.yellow.lsp.parts;

import java.util.function.Function;

import com.schneide.abas.ccd.yellow.lsp.domain.Füllung;

public class Vorratbehälter<F extends Füllung> {

	private final Function<Integer, F> abgabe;
	private int füllstand;

	public Vorratbehälter(Function<Integer, F> abgabe) {
		super();
		this.abgabe = abgabe;
		this.füllstand = 0;
	}

	public void fülleMit(F füllung) {
		this.füllstand += füllung.menge();
	}

	public F hole(int menge) {
		return this.abgabe.apply(Math.min(füllstand, menge));
	}
}
