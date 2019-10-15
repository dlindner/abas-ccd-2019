package com.schneide.abas.ccd.green.lod.printer;

import java.util.ArrayList;
import java.util.List;

public class Printer {

	private final String name;
	private final List<PaperTray> trays;

	public Printer(String name) {
		super();
		this.name = name;
		this.trays = new ArrayList<PaperTray>();
		this.trays.add(new PaperTray("European Letters", PaperSize.a4));
		this.trays.add(new PaperTray("American Letters", PaperSize.letter));
		this.trays.add(new PaperTray("Address Labels", PaperSize.a5, PaperType.labels));
	}

	public boolean isOnline() {
		return true;
	}

	public String name() {
		return name;
	}

	public int trayCount() {
		return this.trays.size();
	}

	public PaperTray tray(int index) {
		return this.trays.get(index);
	}

	public PrintJob spoolAs(String name) {
		return new PrintJob(name);
	}
}
