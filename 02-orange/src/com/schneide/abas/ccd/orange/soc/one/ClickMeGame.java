package com.schneide.abas.ccd.orange.soc.one;

import java.util.Random;

public final class ClickMeGame {

	private ClickMeGame() {
		super();
	}

	public static void main(String[] arguments) {
		final Random rng = new Random(132L);
		final Playground game = new Playground(rng);
		game.start();
	}
}
