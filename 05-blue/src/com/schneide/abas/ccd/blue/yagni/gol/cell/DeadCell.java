package com.schneide.abas.ccd.blue.yagni.gol.cell;

public class DeadCell implements Cell {

	@Override
	public void visit(CellVisitor visitor) {
		visitor.accept(this);
	}
}
