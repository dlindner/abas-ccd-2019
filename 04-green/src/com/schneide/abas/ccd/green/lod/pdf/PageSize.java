package com.schneide.abas.ccd.green.lod.pdf;

import java.awt.Dimension;

public class PageSize {

	private int width;
	private int height;

	public PageSize(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	public Dimension asDimension() {
		return new Dimension(width, height);
	}
}
