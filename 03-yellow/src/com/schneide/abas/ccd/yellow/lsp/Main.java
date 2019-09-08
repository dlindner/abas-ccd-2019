package com.schneide.abas.ccd.yellow.lsp;

import com.schneide.abas.ccd.yellow.lsp.domain.Espressobohnen;
import com.schneide.abas.ccd.yellow.lsp.domain.KaffeeArt;
import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeegetränk;
import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeepulver;

public final class Main {

	public static void main(String[] arguments) {
		final EinfacheKaffeemaschine privileg = new EinfacheKaffeemaschine();

		privileg.fülleWasserAuf(500);
		privileg.füllePulverNach(new Kaffeepulver(30));

		final Kaffeegetränk meinKaffee = privileg.kocheKaffee();
		System.out.println(meinKaffee);

		final TeureKaffeemaschine vertuo = new TeureKaffeemaschine();

		vertuo.fülleWasserAuf(750);
		vertuo.füllePulverNach(new Kaffeepulver(250));
		vertuo.fülleBohnenNach(new Espressobohnen(125));

		final Kaffeegetränk zweiterKaffee = vertuo.kocheKaffee(KaffeeArt.filter);
		System.out.println(zweiterKaffee);
		final Kaffeegetränk dritterKaffee = vertuo.kocheKaffee(KaffeeArt.espresso);
		System.out.println(dritterKaffee);

		// TODO: Führe einfache und teure Kaffeemaschine zusammen!
		/*
		List<Kaffeemaschine> meineMaschinen = new ArrayList<>();
		meineMaschinen.add(privileg);
		meineMaschinen.add(vertuo);

		for (Kaffeemaschine each : meineMaschinen) {
			each.kocheKaffee();
		}
		*/
	}

	private Main() {
		super();
	}
}
