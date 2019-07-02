package com.schneide.abas.ccd.red.fcoi.two.domain;

public class Person {

	private final String name;

	public Person(String name) {
		super();
		this.name = name;
	}

	public String name() {
		return this.name;
	}

	@Override
	public String toString() {
		return name();
	}
}
