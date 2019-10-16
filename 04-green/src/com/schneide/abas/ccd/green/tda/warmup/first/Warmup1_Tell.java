package com.schneide.abas.ccd.green.tda.warmup.first;

import java.util.Arrays;
import java.util.List;

public class Warmup1_Tell {

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
			each.pourFrom(alcohol, water);
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

		public void pourFrom(Champagne alcohol, Seltzer water) {
			if (this.age > 18) {
				alcohol.pour();
				System.out.println(this.name + " drinks " + alcohol);
			} else {
				water.pour();
				System.out.println(this.name + " drinks " + water);
			}
		}

		public void pourFrom2(Champagne alcohol, Seltzer water) {
			Beverage drink = chooseFrom(alcohol, water);
			drink.pour();
			System.out.println(this.name + " drinks " + drink);
		}

		private Beverage chooseFrom(Champagne alcohol, Seltzer water) {
			if (this.age > 18) {
				return alcohol;
			}
			return water;
		}
	}

	private static interface Beverage {
		public void pour();
	}

	private static class Champagne implements Beverage {
		@Override
		public void pour() {
			// does nothing
		}
		@Override
		public String toString() {
			return "champagne";
		}
	}

	private static class Seltzer implements Beverage {
		@Override
		public void pour() {
			// does nothing
		}
		@Override
		public String toString() {
			return "seltzer";
		}
	}
}
