package com.schneide.abas.ccd.red.fcoi.two.features;

import com.schneide.abas.ccd.red.fcoi.two.domain.Person;

public class LeetPerson extends Person {

	public LeetPerson(String name) {
		super(name);
	}

	@Override
	public String name() {
		return super.name()
				    .toLowerCase()
				    .replaceAll("o", "0")
				    .replaceAll("l", "1")
				    .replaceAll("z", "2")
				    .replaceAll("e", "3")
				    .replaceAll("a", "4")
				    .replaceAll("s", "5")
				    .replaceAll("b", "6")
				    .replaceAll("t", "7")
				    .replaceAll("8", "8")
				    .replaceAll("g", "9");
	}
}
