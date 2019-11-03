package com.schneide.abas.ccd.blue.yagni.gol.output;

class ConsolePrintingSetup implements PrintingSetup {

	@Override
	public String rowEndMarker() {
		return "\n";
	}

	@Override
	public char aliveChar() {
		return 'X';
	}

	@Override
	public char deadChar() {
		return '.';
	}

}