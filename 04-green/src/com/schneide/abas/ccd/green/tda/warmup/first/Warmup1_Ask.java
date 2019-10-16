package com.schneide.abas.ccd.green.tda.warmup.first;

import java.util.Arrays;
import java.util.List;

public class Warmup1_Ask {

	public static void main(String[] args) {
		List<Person> course = Arrays.asList(
				new Person("Alice", 25),
				new Person("Bob", 21),
				new Person("Charlie", 14),
				new Person("Dora", 16),
				new Person("Edward", 19),
				new Person("Fanny", 20),
				new Person("George", 33));

		Champagne alcohol = new Champagne();
		Seltzer water = new Seltzer();

		for (Person each : course) {
			if (each.getAge() > 18) {
				alcohol.pour();
				System.out.println(each.getName() + " drinks " + alcohol);
			} else {
				water.pour();
				System.out.println(each.getName() + " drinks " + water);
			}
		}
	}

	private static class Person {

		private final String name;
		private final int age;

		public Person(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}
	}

	private static class Champagne {
		public void pour() {
			// does nothing
		}
		@Override
		public String toString() {
			return "champagne";
		}
	}

	private static class Seltzer {
		public void pour() {
			// does nothing
		}
		@Override
		public String toString() {
			return "seltzer";
		}
	}

}
