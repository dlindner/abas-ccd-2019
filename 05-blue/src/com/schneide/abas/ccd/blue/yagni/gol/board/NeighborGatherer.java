package com.schneide.abas.ccd.blue.yagni.gol.board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.schneide.abas.ccd.blue.yagni.gol.cell.Cell;

public class NeighborGatherer {
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

	public void gatherFor(Board board, NeighborReader reader) {
		for (int y = 0; y < board.height; ++y) {
			for (int x = 0; x < board.width; ++x) {
				reader.read(x, y, gatherFor(board, x, y));
			}
		}
	}
}