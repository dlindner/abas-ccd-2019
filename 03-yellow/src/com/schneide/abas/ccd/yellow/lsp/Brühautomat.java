package com.schneide.abas.ccd.yellow.lsp;

import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeegetränk;
import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeepulver;
import com.schneide.abas.ccd.yellow.lsp.domain.Rezept;
import com.schneide.abas.ccd.yellow.lsp.parts.Brühgruppe;
import com.schneide.abas.ccd.yellow.lsp.parts.Wasserbehälter;

public class Brühautomat {

	private final Wasserbehälter wasserVorrat;
	private final Brühgruppe brühgruppe;

	public Brühautomat() {
		super();
    	this.wasserVorrat = new Wasserbehälter();
        this.brühgruppe = new Brühgruppe();
	}
	
    public void fülleWasserAuf(int wasser) {
    	this.wasserVorrat.fülleMit(wasser);
    }
    
    protected Kaffeegetränk brüheAuf(Rezept rezept, Kaffeepulver pulver) {
    	int wasser = this.wasserVorrat.zapfe(rezept.wasserMenge());
    	int kaffee = this.brühgruppe.brüheAuf(pulver, wasser);
    	return rezept.wendeAnAuf(kaffee);
    }
}
