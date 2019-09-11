package com.schneide.abas.ccd.yellow.lsp;

import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeepulver;
import com.schneide.abas.ccd.yellow.lsp.parts.Brühgruppe;
import com.schneide.abas.ccd.yellow.lsp.parts.Wasserbehälter;

public abstract class Brühmaschine {

	private final Wasserbehälter wasserVorrat;
	private final Brühgruppe brühgruppe;

	public Brühmaschine(Wasserbehälter wasserVorrat, Brühgruppe brühgruppe) {
		super();
		this.wasserVorrat = wasserVorrat;
		this.brühgruppe = brühgruppe;
	}

	public Brühmaschine() {
		super();
		this.wasserVorrat = new Wasserbehälter();
		this.brühgruppe = new Brühgruppe();
	}

	public void fülleWasserAuf(int wasser) {
    	this.wasserVorrat.fülleMit(wasser);
    }
	
	public int zapfe(int wasserMenge) {
		return wasserVorrat.zapfe(wasserMenge);
	}

	public int brüheAuf(Kaffeepulver pulver, int wasser) {
		return brühgruppe.brüheAuf(pulver, wasser);
	}
	
}
