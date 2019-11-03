package com.schneide.abas.ccd.blue.yagni.gol.output;

import com.schneide.abas.ccd.blue.yagni.gol.board.Board;
import com.schneide.abas.ccd.blue.yagni.gol.board.RowwiseBoardHandler;
import com.schneide.abas.ccd.blue.yagni.gol.cell.Cell;
import com.schneide.abas.ccd.blue.yagni.gol.cell.CellVisitor;
import com.schneide.abas.ccd.blue.yagni.gol.cell.DeadCell;
import com.schneide.abas.ccd.blue.yagni.gol.cell.LiveCell;

public class GameOfLifePrintingEngine {

	PrintingSetup setup;
	CellVisitor visitor;

	public GameOfLifePrintingEngine() {
		setup = new ConsolePrintingSetup();
		visitor = new CellVisitor() {

			@Override
			public void accept(LiveCell cell) {
				out(setup.aliveChar());

			}

			@Override
			public void accept(DeadCell cell) {
				out(setup.deadChar());
			}
		};
	}

	private void out(String s) {
		System.out.print(s);

	}

	private void out(char c) {
		System.out.print(c);
	}

	public void print(Board board) {
		RowwiseBoardHandler handler = new RowwiseBoardHandler() {

			@Override
			public void rowEnd() {
				out(setup.rowEndMarker());
			}

			@Override
			public void cell(Cell cell) {
				cell.visit(visitor);
			}
		};

		board.accept(handler);
	}
}