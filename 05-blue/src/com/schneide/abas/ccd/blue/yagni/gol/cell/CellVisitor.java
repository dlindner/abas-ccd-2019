package com.schneide.abas.ccd.blue.yagni.gol.cell;

public interface CellVisitor {
	void accept(DeadCell cell);
	void accept(LiveCell cell);
}
