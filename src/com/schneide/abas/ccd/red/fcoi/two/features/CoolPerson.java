package com.schneide.abas.ccd.red.fcoi.two.features;

import com.schneide.abas.ccd.red.fcoi.two.domain.Person;

public class CoolPerson extends Person {

	public CoolPerson(String name) {
		super(name);
	}

	@Override
	public String name() {
		return "-o-. " + super.name() + " .-o-";
	}
}
