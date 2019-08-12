package com.schneide.abas.ccd.orange.itest.two;

public final class WebBasedClickMeGame {

	private WebBasedClickMeGame() {
		super();
	}

	public static interface RandomnessSource {
		public int randomInteger(int threshold);
	}

	public static void main(String[] arguments) {
		final RandomnessSource rng = new WebBasedRandomnessSource("http://localhost:4567/rng");
		final Playground game = new Playground(rng);
		game.start((0 == arguments.length));
	}
}
