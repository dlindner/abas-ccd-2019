package com.schneide.abas.ccd.green.tda.warmup.second;

import java.util.Arrays;
import java.util.List;

public class Warmup2_Ask {

	public static void main(String[] args) {
		List<GamingConsole> devices = Arrays.asList(
				new Playstation(),
				new XBox(),
				new Switch());

		Gamer joe = new Gamer("Joe", Playstation.class);

		for (GamingConsole each : devices) {
			if (joe.getFavorite() == each.getClass()) {
				joe.give(each);
			}
		}
	}

	private static interface GamingConsole {
		public void haveFun();
	}

	private static class Playstation implements GamingConsole {
		@Override
		public void haveFun() {
			// does nothing
		}
	}

	private static class XBox implements GamingConsole {
		@Override
		public void haveFun() {
			// does nothing
		}
	}

	private static class Switch implements GamingConsole {
		@Override
		public void haveFun() {
			// does nothing
		}
	}

	private static class Gamer {
		private final String name;
		private final Class<? extends GamingConsole> favorite;

		public Gamer(String name, Class<? extends GamingConsole> favorite) {
			this.name = name;
			this.favorite = favorite;
		}

		public void give(GamingConsole console) {
			System.out.println(this.name + " is playing with " + console.getClass().getSimpleName());
			console.haveFun();
		}

		public Class<? extends GamingConsole> getFavorite() {
			return favorite;
		}
	}
}
