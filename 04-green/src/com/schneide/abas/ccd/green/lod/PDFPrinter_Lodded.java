package com.schneide.abas.ccd.green.lod;

import java.awt.Dimension;
import java.io.File;
import java.util.Optional;

import com.schneide.abas.ccd.green.lod.pdf.PDFDocument;
import com.schneide.abas.ccd.green.lod.pdf.PageSize;
import com.schneide.abas.ccd.green.lod.printer.DPI;
import com.schneide.abas.ccd.green.lod.printer.PageOrientation;
import com.schneide.abas.ccd.green.lod.printer.PaperSize;
import com.schneide.abas.ccd.green.lod.printer.PaperTray;
import com.schneide.abas.ccd.green.lod.printer.Printer;

public class PDFPrinter_Lodded {

	private final Printer printer;

	public PDFPrinter_Lodded(Printer hardware) {
		super();
		this.printer = hardware;
	}

	private static class CoolPDFDocument {

		private PDFDocument doc;

		public CoolPDFDocument(PDFDocument doc) {
			this.doc = doc;
		}

		public Dimension pageSize() {
			return this.doc.page().size().asDimension();
		}
	}

	public void printFile(File pdfDocument) {
		// LOD OK: Our friend
		if (!this.printer.isOnline()) {
			// LOD OK: we make, printer friend
			throw new RuntimeException("Printer " + this.printer.name() + " is not ready and/or online.");
		}
		// LOD OK: we make
		final PDFDocument printable = PDFDocument.loadFrom(pdfDocument);
		CoolPDFDocument coolDoc = new CoolPDFDocument(printable);

		// Find best-fitting paper tray
		// LOD OK: our method
		Optional<Integer> bestTray = bestFittingTrayFor(coolDoc.pageSize(), this.printer);
		// ##### LOD NOT OK: bestTray is stranger #####
		int tray = bestTray.orElseThrow(
				// LOD OK: we make Supplier, we make RuntimeException, printer is friend
				() -> new RuntimeException("Printer " + this.printer.name() + " has no paper of matching size."));

		// Rotate the document if necessary
		int rotationAngle = 0;
		// LOD OK: We make
		for (PageOrientation each : PageOrientation.values()) {
			CoolPrinter cool = new CoolPrinter(this.printer, tray);
			if (cool.actualSizeFitsFor(each, coolDoc.pageSize())
					&& (PageOrientation.landscape == each)) {
				rotationAngle = 90;
			}
		}

		// Spool print job
		// LOD OK: printer is friend (always printer)
		boolean success = this.printer.spoolAs("PDF: " + pdfDocument.getName())
						         	  .withResolution(DPI.dpi600)
						         	  .singleSided()
						         	  .inColor()
						         	  .turnedBy(rotationAngle)
						         	  .send();

		if (!success) {
			// LOD OK: we make, printer friend
			throw new RuntimeException("Printer " + this.printer.name() + " cannot print document.");
		}
	}

	private Optional<Integer> bestFittingTrayFor(Dimension requiredSize, Printer printer) {
		// LOD OK: printer given
		for (int index = 0; index < printer.trayCount(); index++) {
			CoolPrinter cool = new CoolPrinter(printer, index);
			// LOD OK: we make PageOrientation
			for (PageOrientation orientation : PageOrientation.values()) {
				if (cool.actualSizeFitsFor(orientation, requiredSize)) {
					// LOD OK: we make Optional, printer friend
					return Optional.of(index);
				}
			}
		}
		// LOD OK: we make Optional
		return Optional.empty();
	}

	private static class CoolPrinter {

		private Printer printer2;
		private int trayIndex;

		public CoolPrinter(Printer printer, int trayIndex) {
			printer2 = printer;
			this.trayIndex = trayIndex;
		}

		public boolean actualSizeFitsFor(PageOrientation orientation, Dimension required) {
			Dimension actual = printer2.tray(trayIndex).properties().size().sizeOf(orientation);
			return ((required.width == actual.width)
					&& (required.height == actual.height));
		}
	}
}
