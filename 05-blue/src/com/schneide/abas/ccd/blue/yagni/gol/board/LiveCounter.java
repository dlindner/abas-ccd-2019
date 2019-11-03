package com.schneide.abas.ccd.blue.yagni.gol.board;

import com.schneide.abas.ccd.blue.yagni.gol.cell.CellVisitor;
import com.schneide.abas.ccd.blue.yagni.gol.cell.DeadCell;
import com.schneide.abas.ccd.blue.yagni.gol.cell.LiveCell;

public class LiveCounter implements CellVisitor {
	private int counter = 0;

	@Override
	public void accept(DeadCell cell) {

	}

	@Override
	public void accept(LiveCell cell) {
		++counter;
	}

	public int get() {
		return counter;
	}
}