package com.schneide.abas.ccd.green.tda.warmup.xtra;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class WarmupX_OurTell {

	public static void main(String[] args) {
		List<Candy> candyJar = Arrays.asList(
				new Candy(Flavor.cherry),
				new Candy(Flavor.citron),
				new Candy(Flavor.melon));

		Child patty = new Child("Patty");
		patty.chooseFrom(
				() -> candyJar.remove(0),
				() -> candyJar.remove(1));
	}

	private static class Child {
		private String name;

		public Child(String name) {
			this.name = name;
		}

		public void chooseFrom(
				Supplier<Candy> left,
				Supplier<Candy> right) {
			if (new Random().nextBoolean()) {
				left.get().eat();
			} else {
				right.get().eat();
			}
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	private static class Candy {
		private final Flavor flavor;

		public Candy(Flavor flavor) {
			this.flavor = flavor;
			System.out.println(this + " is bought.");
		}

		public void eat() {
			System.out.println(this + " gets eaten.");
		}

		@Override
		public String toString() {
			return this.flavor + "-flavored candy";
		}
	}

	private static enum Flavor {
		cherry,
		banana,
		citron,
		raspberry,
		melon;
	}
}
