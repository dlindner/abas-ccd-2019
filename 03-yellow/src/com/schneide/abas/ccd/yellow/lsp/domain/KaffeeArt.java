package com.schneide.abas.ccd.yellow.lsp.domain;

// marker
public interface KaffeeArt {
	
	public static enum KaffeeAuswahlEinfach implements KaffeeArt {
		filter
	}
	
	public static enum KaffeeAuswahlTeuer implements KaffeeArt {
		filter,
		espresso;
	}
	
}

