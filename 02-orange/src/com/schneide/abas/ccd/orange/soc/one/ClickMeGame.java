package com.schneide.abas.ccd.orange.soc.one;

import java.util.Random;

public final class ClickMeGame {

	private ClickMeGame() {
		super();
	}

	public static void main(String[] arguments) {
		final Random rng = new Random(132L) {
			public int nextInt(int bound) {
				int result = super.nextInt(bound);
				System.out.println("rng: " + bound + " --> " + result);
				return result;
			};
		};
		final Playground game = new Playground(rng);
		game.start((0 == arguments.length));
	}
}
