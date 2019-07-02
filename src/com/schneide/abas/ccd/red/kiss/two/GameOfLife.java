package com.schneide.abas.ccd.red.kiss.two;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameOfLife {

	static interface RowwiseBoardHandler {
		void cell(Cell cell);
		void rowEnd();
	}

	static class Board {
		private final Map<Integer, Cell> map;
		public final int width;
		public final int height;

		private Integer encode(int x, int y) {
			return y * width + x;
		}

		private Board(int width, int height, Map<Integer, Cell> map) {
			super();
			this.width = width;
			this.height = height;
			this.map = map;
		}

		public Board(Board other) {
			this(other.width, other.height, new HashMap<Integer, Cell>(other.map));
		}

		public Board(int width, int height) {
			this(width, height, new HashMap<Integer, Cell>());

			for (int y = 0; y < height; ++y) {
				for (int x = 0; x < width; ++x) {
					this.map.put(encode(x, y), new DeadCell());
				}
			}
		}

		public Cell getCell(int x, int y) {
			if (outOfBounds(x, y)) {
				return new DeadCell();
			}
			if (!this.map.containsKey(encode(x, y))) {
				return new DeadCell();
			}
			return this.map.get(encode(x, y));
		}

		public void accept(RowwiseBoardHandler handler) {
			for (int y = 0; y < height; ++y) {
				for (int x = 0; x < width; ++x) {
					handler.cell(getCell(x, y));
				}
				handler.rowEnd();
			}
		}

		private boolean outOfBounds(int x, int y) {
			return x < 0 || x >= width || y < 0 || y >= height;
		}

		public void setCell(int x, int y, Cell cell) {
			if (outOfBounds(x, y)) {
				throw new IndexOutOfBoundsException();
			}
			this.map.put(encode(x, y), cell);
		}
	}

	interface PrintingSetup {
		String rowEndMarker();
		char aliveChar();
		char deadChar();
	}

	static class ConsolePrintingSetup implements PrintingSetup {

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

	public static class GameOfLifePrintingEngine {

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

		void print(Board board) {
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

	public static class Offset {
		public int x;
		public int y;

		public Offset(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static interface NeighborReader {
		void read(int x, int y, List<Cell> neighbors);

	}

	static class LiveCounter implements CellVisitor {
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

	class NeighborGatherer {
		List<Offset> offsets;

		public NeighborGatherer() {
			offsets = new ArrayList<Offset>();
			offsets.add(new Offset(-1, -1));
			offsets.add(new Offset(-1, 0));
			offsets.add(new Offset(-1, 1));

			offsets.add(new Offset(0, -1));
			offsets.add(new Offset(0, 1));

			offsets.add(new Offset(1, -1));
			offsets.add(new Offset(1, 0));
			offsets.add(new Offset(1, 1));
		}

		List<Cell> gatherFor(Board board, int x, int y) {
			return offsets.stream().map(offset -> board.getCell(x+offset.x, y+offset.y)).collect(Collectors.toList());
		}

		void gatherFor(Board board, NeighborReader reader) {
			for (int y = 0; y < board.height; ++y) {
				for (int x = 0; x < board.width; ++x) {
					reader.read(x, y, gatherFor(board, x, y));
				}
			}
		}
	}

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
