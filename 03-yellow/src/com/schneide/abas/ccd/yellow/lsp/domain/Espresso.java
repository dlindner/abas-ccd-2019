package com.schneide.abas.ccd.yellow.lsp.domain;

public class Espresso extends Kaffeegetränk {

	public Espresso(int menge) {
		super(menge);
	}

	@Override
	protected String bezeichnung() {
		return "Espressos";
	}
}
