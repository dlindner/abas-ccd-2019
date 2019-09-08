package com.schneide.abas.ccd.yellow.lsp.domain;

public class Espressobohnen implements Füllung {

	private final int menge;

	public Espressobohnen(int menge) {
		this.menge = menge;
	}

	@Override
	public int menge() {
		return this.menge;
	}
}
