package com.schneide.abas.ccd.orange.itest.one.cut;

import java.util.Random;

public final class TestedClickMeGame {

	private TestedClickMeGame() {
		super();
	}

	public static void main(String[] arguments) {
		final Random rng = new Random(132L);
//		final Random rng = new Random(132L) {
//			private static final long serialVersionUID = -8817787675423572173L;
//
//			public int nextInt(int bound) {
//				int result = super.nextInt(bound);
//				System.out.println("rng: " + bound + " --> " + result);
//				return result;
//			};
//		};
		final Playground game = new Playground(rng);
		game.start((0 == arguments.length));
	}
}
