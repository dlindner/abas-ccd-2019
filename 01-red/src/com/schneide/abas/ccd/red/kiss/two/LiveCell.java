package com.schneide.abas.ccd.red.kiss.two;

public class LiveCell implements Cell {

	@Override
	public void visit(CellVisitor visitor) {
		visitor.accept(this);
	}
}
