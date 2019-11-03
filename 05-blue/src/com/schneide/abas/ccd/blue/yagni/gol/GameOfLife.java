package com.schneide.abas.ccd.blue.yagni.gol;

import java.util.List;

import com.schneide.abas.ccd.blue.yagni.gol.board.Board;
import com.schneide.abas.ccd.blue.yagni.gol.board.LiveCounter;
import com.schneide.abas.ccd.blue.yagni.gol.board.NeighborGatherer;
import com.schneide.abas.ccd.blue.yagni.gol.board.NeighborReader;
import com.schneide.abas.ccd.blue.yagni.gol.cell.Cell;
import com.schneide.abas.ccd.blue.yagni.gol.cell.CellVisitor;
import com.schneide.abas.ccd.blue.yagni.gol.cell.DeadCell;
import com.schneide.abas.ccd.blue.yagni.gol.cell.LiveCell;
import com.schneide.abas.ccd.blue.yagni.gol.output.GameOfLifePrintingEngine;

public class GameOfLife {

	Board evolve(Board current) {
		Board next = new Board(current.width, current.height);
		NeighborGatherer gatherer = new NeighborGatherer();
		gatherer.gatherFor(current, new NeighborReader() {

			@Override
			public void read(int x, int y, List<Cell> neighbors) {
				Cell self = current.getCell(x, y);
				LiveCounter counter = new LiveCounter();
				for (Cell neighbor : neighbors) {
					neighbor.visit(counter);
				}

				self.visit(new CellVisitor() {

					@Override
					public void accept(LiveCell cell) {
						if (counter.get() == 2 || counter.get() == 3) {
							next.setCell(x, y, new LiveCell());
						}
					}

					@Override
					public void accept(DeadCell cell) {
						if (counter.get() == 3) {
							next.setCell(x, y, new LiveCell());
						}
					}
				});

			}
		});
		return next;
	}

	void run() {
		System.out.println("Hello game of life!");

		Board board = startingBoard();
		print(board);

		for (int i = 0; i < 15; ++i)
		{
			System.out.println("");
			board = evolve(board);
			print(board);
		}
	}

	private Board startingBoard() {
		Board board = new Board(8, 8);
		board.setCell(6, 1, new LiveCell());
		board.setCell(4, 2, new LiveCell());
		board.setCell(6, 2, new LiveCell());
		board.setCell(7, 2, new LiveCell());
		board.setCell(4, 3, new LiveCell());
		board.setCell(6, 3, new LiveCell());
		board.setCell(4, 4, new LiveCell());
		board.setCell(2, 5, new LiveCell());
		board.setCell(0, 6, new LiveCell());
		board.setCell(2, 6, new LiveCell());
		return board;
	}

	private void print(Board board) {
		new GameOfLifePrintingEngine().print(board);
	}

	public static void main(String[] args) {
		new GameOfLife().run();
	}
}

