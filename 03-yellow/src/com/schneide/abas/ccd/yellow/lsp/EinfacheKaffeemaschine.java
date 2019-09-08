package com.schneide.abas.ccd.yellow.lsp;

import com.schneide.abas.ccd.yellow.lsp.domain.Filterkaffee;
import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeegetränk;
import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeepulver;
import com.schneide.abas.ccd.yellow.lsp.domain.Rezept;
import com.schneide.abas.ccd.yellow.lsp.parts.Brühgruppe;
import com.schneide.abas.ccd.yellow.lsp.parts.Vorratbehälter;
import com.schneide.abas.ccd.yellow.lsp.parts.Wasserbehälter;

public class EinfacheKaffeemaschine {

	private final Wasserbehälter wasserVorrat;
    private final Vorratbehälter<Kaffeepulver> kaffeeVorrat;
    private final Brühgruppe brühgruppe;
    private final Rezept rezept;

    public EinfacheKaffeemaschine() {
    	super();
    	this.wasserVorrat = new Wasserbehälter();
        this.kaffeeVorrat = new Vorratbehälter<Kaffeepulver>(Kaffeepulver::new);
        this.brühgruppe = new Brühgruppe();
        this.rezept = new Rezept(12, 200, Filterkaffee::new);
    }

    public void fülleWasserAuf(int wasser) {
    	this.wasserVorrat.fülleMit(wasser);
    }

    public void füllePulverNach(Kaffeepulver neuesPulver) {
    	this.kaffeeVorrat.fülleMit(neuesPulver);
    }

    public Kaffeegetränk kocheKaffee() {
    	Kaffeepulver pulver = this.kaffeeVorrat.hole(this.rezept.kaffeeMenge());
    	int wasser = this.wasserVorrat.zapfe(this.rezept.wasserMenge());

    	int kaffee = this.brühgruppe.brüheAuf(pulver, wasser);
    	return new Filterkaffee(kaffee);
    }
}
