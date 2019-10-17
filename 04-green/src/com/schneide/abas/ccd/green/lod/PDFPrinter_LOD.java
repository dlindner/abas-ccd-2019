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

public class PDFPrinter_LOD {

	private final Printer printer;

	public PDFPrinter_LOD(Printer hardware) {
		super();
		this.printer = hardware;
	}

	public void printFile(File pdfDocument) {
		// LOD OK: Our friend
		if (!this.printer.isOnline()) {
			// LOD OK: we make, printer friend
			throw new RuntimeException("Printer " + this.printer.name() + " is not ready and/or online.");
		}
		// LOD OK: we make
		final PDFDocument printable = PDFDocument.loadFrom(pdfDocument);

		// Find best-fitting paper tray
		// ##### LOD NOT OK: PDFPage is stranger #####
		PageSize pageSize = printable.page().size();

		// LOD OK: our method
		Optional<PaperTray> bestTray = bestFittingTrayFor(pageSize, this.printer);
		// ##### LOD NOT OK: bestTray is stranger #####
		PaperTray tray = bestTray.orElseThrow(
				// LOD OK: we make Supplier, we make RuntimeException, printer is friend
				() -> new RuntimeException("Printer " + this.printer.name() + " has no paper of matching size."));

		// Rotate the document if necessary
		// ##### LOD NOT OK: PaperTray is stranger, PageProperties is stranger #####
		PaperSize paperSize = tray.properties().size();
		// ##### LOD NOT OK: Dimension is stranger #####
		Dimension wanted = pageSize.asDimension();
		int rotationAngle = 0;
		// LOD OK: We make
		for (PageOrientation each : PageOrientation.values()) {
			// ##### LOD NOT OK: papierSize is stranger #####
			Dimension gotten = paperSize.sizeOf(each);
			// ##### LOD NOT OK: Dimension is stranger #####
			if ((gotten.height == wanted.height)
					&& (gotten.width == wanted.width)) {
				if (PageOrientation.landscape == each) {
					rotationAngle = 90;
				}
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

	private Optional<PaperTray> bestFittingTrayFor(PageSize requiredSize, Printer printer) {
		// LOD OK: printer given
		for (int index = 0; index < printer.trayCount(); index++) {
			// ##### LOD NOT OK: tray is stranger, properties is stranger #####
			PaperSize actualSize = printer.tray(index).properties().size();
			// LOD OK: requiredSize given
			Dimension required = requiredSize.asDimension();
			// LOD OK: we make PageOrientation
			for (PageOrientation orientation : PageOrientation.values()) {
				// ##### LOD NOT OK: actualSize is stranger #####
				Dimension actual = actualSize.sizeOf(orientation);
				// ##### LOD NOT OK: required and actual are strangers! #####
				if ((required.width == actual.width)
						&& (required.height == actual.height)) {
					// LOD OK: we make Optional, printer friend
					return Optional.of(printer.tray(index));
				}
			}
		}
		// LOD OK: we make Optional
		return Optional.empty();
	}
}
