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

public class PrintProcess {

	public PrintProcess() {
		super();
	}

	public void printOn(
			Printer printer,
			File document) {
		if (!printer.isOnline()) {
			throw new RuntimeException("Printer " + printer.name() + " is not ready and/or online.");
		}
		final PDFDocument printable = PDFDocument.loadFrom(document);

		// Find best-fitting paper tray
		PageSize pageSize = printable.page().size();
		Optional<PaperTray> bestTray = bestFittingTrayFor(pageSize, printer);
		PaperTray tray = bestTray.orElseThrow(
				() -> new RuntimeException("Printer " + printer.name() + " has no paper of matching size."));

		// Rotate the document if necessary
		PaperSize paperSize = tray.properties().size();
		Dimension wanted = pageSize.asDimension();
		int rotationAngle = 0;
		for (PageOrientation each : PageOrientation.values()) {
			Dimension gotten = paperSize.sizeOf(each);
			if ((gotten.height == wanted.height)
					&& (gotten.width == wanted.width)) {
				if (PageOrientation.landscape == each) {
					rotationAngle = 90;
				}
			}
		}

		boolean success = printer.spoolAs("PDF: " + document.getName())
						         .withResolution(DPI.dpi600)
						         .singleSided()
						         .inColor()
						         .turnedBy(rotationAngle)
						         .send();
		if (!success) {
			throw new RuntimeException("Printer " + printer.name() + " cannot print document.");
		}
	}

	private Optional<PaperTray> bestFittingTrayFor(PageSize requiredSize, Printer printer) {
		for (int index = 0; index < printer.trayCount(); index++) {
			PaperSize actualSize = printer.tray(index).properties().size();
			Dimension required = requiredSize.asDimension();
			for (PageOrientation orientation : PageOrientation.values()) {
				Dimension actual = actualSize.sizeOf(orientation);
				if ((required.width == actual.width)
						&& (required.height == actual.height)) {
					return Optional.of(printer.tray(index));
				}
			}
		}
		return Optional.empty();
	}
}