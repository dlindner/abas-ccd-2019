package com.schneide.abas.ccd.red.kiss.two;

public interface CellVisitor {

	void accept(DeadCell cell);

	void accept(LiveCell cell);
}
