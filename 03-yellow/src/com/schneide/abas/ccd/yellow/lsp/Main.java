package com.schneide.abas.ccd.yellow.lsp;

import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeegetränk;
import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeepulver;

public final class Main {

	public static void main(String[] arguments) {
		final EinfacheKaffeeMaschine privileg = new EinfacheKaffeeMaschine();

		privileg.fülleWasserAuf(500);
		privileg.füllePulverNach(new Kaffeepulver(30));

		final Kaffeegetränk meinKaffee = privileg.kocheKaffee();
		System.out.println(meinKaffee);


	}

	private Main() {
		super();
	}
}
