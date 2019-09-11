package com.schneide.abas.ccd.yellow.lsp;

import java.util.HashMap;
import java.util.Map;

import com.schneide.abas.ccd.yellow.lsp.domain.Espresso;
import com.schneide.abas.ccd.yellow.lsp.domain.Espressobohnen;
import com.schneide.abas.ccd.yellow.lsp.domain.Filterkaffee;
import com.schneide.abas.ccd.yellow.lsp.domain.KaffeeArt;
import com.schneide.abas.ccd.yellow.lsp.domain.KaffeeArt.KaffeeAuswahlTeuer;
import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeegetränk;
import com.schneide.abas.ccd.yellow.lsp.domain.Kaffeepulver;
import com.schneide.abas.ccd.yellow.lsp.domain.Rezept;
import com.schneide.abas.ccd.yellow.lsp.parts.Mahlwerk;
import com.schneide.abas.ccd.yellow.lsp.parts.Vorratbehälter;

public class TeureKaffeemaschine extends AbstractKaffeeMaschine<KaffeeAuswahlTeuer> {

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
        		KaffeeAuswahlTeuer.filter,
        		new Rezept(12, 200, Filterkaffee::new));
        this.rezepte.put(
        		KaffeeAuswahlTeuer.espresso,
        		new Rezept(7, 30, Espresso::new));
    }

    public void fülleBohnenNach(Espressobohnen neueBohnen) {
    	this.bohnenVorrat.fülleMit(neueBohnen);
    }

    public void füllePulverNach(Kaffeepulver neuesPulver) {
    	this.pulverVorrat.fülleMit(neuesPulver);
    }

    @Override
    public Kaffeegetränk kocheKaffee(KaffeeAuswahlTeuer auswahl) {
    	Rezept rezept = this.rezepte.get(auswahl);
    	Kaffeepulver pulver = pulverFür(auswahl, rezept.kaffeeMenge());
    	int wasser = this.zapfe(rezept.wasserMenge());

    	int kaffee = this.brüheAuf(pulver, wasser);
    	return rezept.wendeAnAuf(kaffee);
    }

    private Kaffeepulver pulverFür(KaffeeAuswahlTeuer auswahl, int menge) {
    	if (KaffeeAuswahlTeuer.filter == auswahl) {
    		return this.pulverVorrat.hole(menge);
    	}
    	Espressobohnen bohnen = this.bohnenVorrat.hole(menge);
    	Kaffeepulver gemahlen = this.mühle.mahle(bohnen);
    	return gemahlen;
    }

}
