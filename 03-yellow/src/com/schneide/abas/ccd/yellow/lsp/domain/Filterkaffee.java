package com.schneide.abas.ccd.yellow.lsp.domain;

public class Filterkaffee extends Kaffeegetränk {

	public Filterkaffee(int menge) {
		super(menge);
	}

	@Override
	protected String bezeichnung() {
		return "Filterkaffees";
	}
}
