package com.schneide.abas.ccd.blue.yagni.gol.board;

import java.util.HashMap;
import java.util.Map;

import com.schneide.abas.ccd.blue.yagni.gol.cell.Cell;
import com.schneide.abas.ccd.blue.yagni.gol.cell.DeadCell;

public class Board {
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