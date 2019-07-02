package com.schneide.abas.ccd.red.fcoi.two.features;

import com.schneide.abas.ccd.red.fcoi.two.domain.Person;

public class SquarePerson extends Person {

	public SquarePerson(String name) {
		super(name);
	}

	@Override
	public String name() {
		return super.name() + "<>" + super.name();
	}
}
