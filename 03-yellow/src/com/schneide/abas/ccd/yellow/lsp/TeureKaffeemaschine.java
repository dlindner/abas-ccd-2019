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

public class TeureKaffeemaschine {

	private final Wasserbehälter wasserVorrat;
	private final Vorratbehälter<Kaffeepulver> pulverVorrat;
	private final Vorratbehälter<Espressobohnen> bohnenVorrat;
	private final Mahlwerk mühle;
    private final Brühgruppe brühgruppe;
    private final Map<KaffeeArt, Rezept> rezepte;

    public TeureKaffeemaschine() {
    	super();
    	this.wasserVorrat = new Wasserbehälter();
    	this.pulverVorrat = new Vorratbehälter<>(Kaffeepulver::new);
    	this.bohnenVorrat = new Vorratbehälter<>(Espressobohnen::new);
        this.mühle = new Mahlwerk();
        this.brühgruppe = new Brühgruppe();
        this.rezepte = new HashMap<>();
        this.rezepte.put(
        		KaffeeArt.filter,
        		new Rezept(12, 200, Filterkaffee::new));
        this.rezepte.put(
        		KaffeeArt.espresso,
        		new Rezept(7, 30, Espresso::new));
    }

    public void fülleWasserAuf(int wasser) {
    	this.wasserVorrat.fülleMit(wasser);
    }

    public void fülleBohnenNach(Espressobohnen neueBohnen) {
    	this.bohnenVorrat.fülleMit(neueBohnen);
    }

    public void füllePulverNach(Kaffeepulver neuesPulver) {
    	this.pulverVorrat.fülleMit(neuesPulver);
    }

    public Kaffeegetränk kocheKaffee(KaffeeArt auswahl) {
    	Rezept rezept = this.rezepte.get(auswahl);
    	Kaffeepulver pulver = pulverFür(auswahl, rezept.kaffeeMenge());
    	int wasser = this.wasserVorrat.zapfe(rezept.wasserMenge());

    	int kaffee = this.brühgruppe.brüheAuf(pulver, wasser);
    	return rezept.wendeAnAuf(kaffee);
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
