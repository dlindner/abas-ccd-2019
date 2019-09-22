package com.schneide.abas.ccd.yellow.lsp;

import java.util.HashMap;
import java.util.Map;

import com.schneide.abas.ccd.yellow.lsp.domain.Espresso;
import com.schneide.abas.ccd.yellow.lsp.domain.Espressobohnen;
import com.schneide.abas.ccd.yellow.lsp.domain.Filterkaffee;
import com.schneide.abas.ccd.yellow.lsp.domain.KaffeeArt;
import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeegetränk;
import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeepulver;
import com.schneide.abas.ccd.yellow.lsp.domain.Rezept;
import com.schneide.abas.ccd.yellow.lsp.parts.Brühgruppe;
import com.schneide.abas.ccd.yellow.lsp.parts.Mahlwerk;
import com.schneide.abas.ccd.yellow.lsp.parts.Vorratbehälter;
import com.schneide.abas.ccd.yellow.lsp.parts.Wasserbehälter;

public class TeureKaffeemaschine extends Kaffeemaschinenbasis {

	private final Vorratbehälter<Kaffeepulver> pulverVorrat;
	private final Vorratbehälter<Espressobohnen> bohnenVorrat;
	private final Mahlwerk mühle;
    private final Map<KaffeeArt, Rezept> rezepte;

    public TeureKaffeemaschine() {
    	super();
    	this.pulverVorrat = new Vorratbehälter<>(Kaffeepulver::new);
    	this.bohnenVorrat = new Vorratbehälter<>(Espressobohnen::new);
        this.mühle = new Mahlwerk();
        this.rezepte = new HashMap<>();
        this.rezepte.put(
        		KaffeeArt.filter,
        		new Rezept(12, 200, Filterkaffee::new));
        this.rezepte.put(
        		KaffeeArt.espresso,
        		new Rezept(7, 30, Espresso::new));
    }

    public void fülleBohnenNach(Espressobohnen neueBohnen) {
    	this.bohnenVorrat.fülleMit(neueBohnen);
    }

    public void füllePulverNach(Kaffeepulver neuesPulver) {
    	this.pulverVorrat.fülleMit(neuesPulver);
    }
    
    @Override
    public Kaffeegetränk kocheFilterkaffee() {
    	return kocheKaffee(KaffeeArt.filter);
    }

    @Override
    public Kaffeegetränk kocheKaffee(KaffeeArt auswahl) {
    	Rezept rezept = this.rezepte.get(auswahl);
    	Kaffeepulver pulver = pulverFür(auswahl, rezept.kaffeeMenge());
    	return brüheAuf(rezept, pulver);
    }

    private Kaffeepulver pulverFür(KaffeeArt auswahl, int menge) {
    	if (KaffeeArt.filter == auswahl) {
    		return this.pulverVorrat.hole(menge);
    	}
    	Espressobohnen bohnen = this.bohnenVorrat.hole(menge);
    	Kaffeepulver gemahlen = this.mühle.mahle(bohnen);
    	return gemahlen;
    }
}
