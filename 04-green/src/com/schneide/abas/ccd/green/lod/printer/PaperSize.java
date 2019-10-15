package com.schneide.abas.ccd.green.lod.printer;

import java.awt.Dimension;

public enum PaperSize {

	a5(148, 210),
	a4(210, 297),
	letter(216, 279),
	legal(216, 356);

	private int width;
	private int height;

	private PaperSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public Dimension sizeOf(PageOrientation orientation) {
		if (PageOrientation.portrait == orientation) {
			return new Dimension(width, height);
		}
		return new Dimension(height, width);
	}
}
