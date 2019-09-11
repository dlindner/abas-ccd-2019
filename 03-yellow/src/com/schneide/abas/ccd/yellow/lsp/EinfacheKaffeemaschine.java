package com.schneide.abas.ccd.yellow.lsp;

import com.schneide.abas.ccd.yellow.lsp.domain.Filterkaffee;
import com.schneide.abas.ccd.yellow.lsp.domain.KaffeeArt.KaffeeAuswahlEinfach;
import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeegetränk;
import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeepulver;
import com.schneide.abas.ccd.yellow.lsp.domain.Rezept;
import com.schneide.abas.ccd.yellow.lsp.parts.Vorratbehälter;

public class EinfacheKaffeemaschine extends AbstractKaffeeMaschine<KaffeeAuswahlEinfach> {
	
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
    public Kaffeegetränk kocheKaffee(KaffeeAuswahlEinfach auswahl) {
    	Kaffeepulver pulver = this.kaffeeVorrat.hole(this.rezept.kaffeeMenge());
    	int wasser = this.zapfe(this.rezept.wasserMenge());

    	int kaffee = this.brüheAuf(pulver, wasser);
    	return new Filterkaffee(kaffee);
    }
    
    public Kaffeegetränk kocheKaffee() {
    	return kocheKaffee(KaffeeAuswahlEinfach.filter);
    }

}
