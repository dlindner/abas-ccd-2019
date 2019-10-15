package com.schneide.abas.ccd.green.lod.printer;

public class PaperTray {

	private String name;
	private PaperSize paper;
	private PageOrientation orientation;
	private PaperType type;

	public PaperTray(
			String name,
			PaperSize paper) {
		this(name, paper, PaperType.normal);
	}

	public PaperTray(
			String name,
			PaperSize paper,
			PaperType type) {
		super();
		this.name = name;
		this.paper = paper;
		this.type = type;
		this.orientation = PageOrientation.portrait;
	}

	public String name() {
		return name;
	}

	public PageProperties properties() {
		return new PageProperties() {
			@Override
			public PaperSize size() {
				return paper;
			}
			@Override
			public PageOrientation orientation() {
				return orientation;
			}
			@Override
			public PaperType type() {
				return type;
			}
		};
	}
}
