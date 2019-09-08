package com.schneide.abas.ccd.yellow.lsp.domain;

public abstract class Kaffeegetränk {

	private final int menge;

	public Kaffeegetränk(int menge) {
		super();
		this.menge = menge;
	}

	protected abstract String bezeichnung();

	@Override
	public String toString() {
		return "Leckere " + this.menge + " ml frisch gebrühten " + bezeichnung();
	}
}
