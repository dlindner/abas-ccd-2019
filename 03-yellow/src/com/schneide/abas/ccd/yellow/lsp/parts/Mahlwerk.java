package com.schneide.abas.ccd.yellow.lsp.parts;

import com.schneide.abas.ccd.yellow.lsp.domain.Espressobohnen;
import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeepulver;

public class Mahlwerk {

	public Mahlwerk() {
		super();
	}

	public Kaffeepulver mahle(Espressobohnen bohnen) {
		return new Kaffeepulver(bohnen.menge());
	}
}
