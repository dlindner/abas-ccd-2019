package com.schneide.abas.ccd.green.tda.warmup.xtra;

import java.util.Random;
import java.util.function.Supplier;

public class WarmupX_Tell {

	public static void main(String[] args) {
		Child patty = new Child("Patty");
		patty.chooseFrom(
				() -> new Candy(Flavor.cherry),
				() -> new Candy(Flavor.citron));
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
				return;
			}
			right.get().eat();
		}

		public void chooseFrom2(
				Supplier<Candy> left,
				Supplier<Candy> right) {
			Supplier<Candy> chosen = new Random().nextBoolean() ? left : right;
			chosen.get().eat();
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
