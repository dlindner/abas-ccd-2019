package com.schneide.abas.ccd.yellow.lsp;

import com.schneide.abas.ccd.yellow.lsp.domain.KaffeeArt;
import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeegetränk;

public abstract class AbstractKaffeeMaschine<T extends Enum & KaffeeArt> extends Brühmaschine {

	public abstract Kaffeegetränk kocheKaffee(T auswahl);
	
}
