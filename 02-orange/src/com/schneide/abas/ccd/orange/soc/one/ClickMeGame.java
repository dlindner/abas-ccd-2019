package com.schneide.abas.ccd.orange.soc.one;

import java.util.Random;

public final class ClickMeGame {

	private ClickMeGame() {
		super();
	}

	public static void main(String[] arguments) {
		String isLoopingText = (null == arguments[0]) ? "true" : arguments[0];
		final boolean isLooping = Boolean.parseBoolean(isLoopingText);

		final Random rng = new Random(132L);
		final Playground game = new Playground(rng);
		game.start(isLooping);
	}
}
