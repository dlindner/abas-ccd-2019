package com.schneide.abas.ccd.blue.yagni.gol.board;

import com.schneide.abas.ccd.blue.yagni.gol.cell.Cell;

public interface RowwiseBoardHandler {
	void cell(Cell cell);
	void rowEnd();
}