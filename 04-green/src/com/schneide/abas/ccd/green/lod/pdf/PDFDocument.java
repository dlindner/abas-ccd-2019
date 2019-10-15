package com.schneide.abas.ccd.green.lod.pdf;

import java.io.File;

public class PDFDocument {

	public static PDFDocument loadFrom(File file) {
		return new PDFDocument();
	}

	public PDFPage page() {
		return new PDFPage();
	}
}
