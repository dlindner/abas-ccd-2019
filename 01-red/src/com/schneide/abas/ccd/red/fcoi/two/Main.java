package com.schneide.abas.ccd.red.fcoi.two;

import com.schneide.abas.ccd.red.fcoi.two.domain.Person;
import com.schneide.abas.ccd.red.fcoi.two.features.CoolPerson;
import com.schneide.abas.ccd.red.fcoi.two.features.LeetPerson;
import com.schneide.abas.ccd.red.fcoi.two.features.SquarePerson;

public class Main {

	public static void main(String[] args) {
		final String name = "Hans";

		System.out.println(new Person(name));
		System.out.println(new CoolPerson(name));
		System.out.println(new LeetPerson(name));
		System.out.println(new SquarePerson(name));
	}
}
