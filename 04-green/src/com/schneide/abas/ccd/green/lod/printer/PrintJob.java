package com.schneide.abas.ccd.green.lod.printer;

public class PrintJob {

	private String name;
	private int printedSides;
	private DPI resolution;
	private boolean blackAndWhite;
	private int rotationAngle;

	public PrintJob(String name) {
		super();
		this.name = name;
		this.printedSides = 1;
		this.resolution = DPI.dpi300;
		this.blackAndWhite = false;
		this.rotationAngle = 0;
	}

	public PrintJob singleSided() {
		this.printedSides = 1;
		return this;
	}

	public PrintJob doubleSided() {
		this.printedSides = 2;
		return this;
	}

	public PrintJob withResolution(DPI newResolution) {
		this.resolution = newResolution;
		return this;
	}

	public PrintJob inColor() {
		this.blackAndWhite = false;
		return this;
	}

	public PrintJob inBlackAndWhite() {
		this.blackAndWhite = true;
		return this;
	}

	public PrintJob turnedBy(int angle) {
		this.rotationAngle = angle;
		return this;
	}

	public boolean send() {
		return true;
	}
}
