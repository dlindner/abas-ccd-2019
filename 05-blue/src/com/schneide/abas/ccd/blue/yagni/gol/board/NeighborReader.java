package com.schneide.abas.ccd.blue.yagni.gol.board;

import java.util.List;

import com.schneide.abas.ccd.blue.yagni.gol.cell.Cell;

public interface NeighborReader {
	void read(int x, int y, List<Cell> neighbors);

}