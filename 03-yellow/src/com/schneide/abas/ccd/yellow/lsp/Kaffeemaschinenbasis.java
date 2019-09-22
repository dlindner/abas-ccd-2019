package com.schneide.abas.ccd.yellow.lsp;

import com.schneide.abas.ccd.yellow.lsp.domain.KaffeeArt;
import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeegetränk;
import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeepulver;
import com.schneide.abas.ccd.yellow.lsp.domain.Rezept;

public abstract class Kaffeemaschinenbasis<A> {

	private final Brühautomat brühung;
	
	public Kaffeemaschinenbasis() {
		super();
		this.brühung = new Brühautomat();
	}
	
	public abstract Kaffeegetränk kocheFilterkaffee();
	
	public abstract Kaffeegetränk kocheKaffee(A sorte);
	
    public void fülleWasserAuf(int wasser) {
    	this.brühung.fülleWasserAuf(wasser);
    }
    
    protected Kaffeegetränk brüheAuf(Rezept rezept, Kaffeepulver pulver) {
    	return this.brühung.brüheAuf(rezept, pulver);
    }
}
