package com.schneide.abas.ccd.yellow.lsp.domain;

public class Kaffeepulver implements Füllung {

	private final int menge;

	public Kaffeepulver(int menge) {
		this.menge = menge;
	}

	@Override
	public int menge() {
		return this.menge;
	}
}
