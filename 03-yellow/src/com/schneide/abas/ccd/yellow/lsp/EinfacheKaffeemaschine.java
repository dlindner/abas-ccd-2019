package com.schneide.abas.ccd.yellow.lsp;

import com.schneide.abas.ccd.yellow.lsp.domain.Filterkaffee;
import com.schneide.abas.ccd.yellow.lsp.domain.KaffeeArt;
import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeegetränk;
import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeepulver;
import com.schneide.abas.ccd.yellow.lsp.domain.Rezept;
import com.schneide.abas.ccd.yellow.lsp.parts.Vorratbehälter;

public class EinfacheKaffeemaschine extends Kaffeemaschinenbasis {

    private final Vorratbehälter<Kaffeepulver> kaffeeVorrat;
    private final Rezept rezept;

    public EinfacheKaffeemaschine() {
    	super();
        this.kaffeeVorrat = new Vorratbehälter<Kaffeepulver>(Kaffeepulver::new);
        this.rezept = new Rezept(12, 200, Filterkaffee::new);
    }

    public void füllePulverNach(Kaffeepulver neuesPulver) {
    	this.kaffeeVorrat.fülleMit(neuesPulver);
    }

    @Override
    public Kaffeegetränk kocheFilterkaffee() {
    	Kaffeepulver pulver = this.kaffeeVorrat.hole(this.rezept.kaffeeMenge());
    	return brüheAuf(this.rezept, pulver);
    }
    
    @Override
    public Kaffeegetränk kocheKaffee(KaffeeArt sorte) {
    	if (KaffeeArt.filter == sorte) {
    		return kocheFilterkaffee();
    	}
    	throw new UnsupportedOperationException();
    }
}
