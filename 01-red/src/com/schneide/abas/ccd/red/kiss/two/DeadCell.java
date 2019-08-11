package com.schneide.abas.ccd.red.kiss.two;

public class DeadCell implements Cell {

	@Override
	public void visit(CellVisitor visitor) {
		visitor.accept(this);
	}
}
