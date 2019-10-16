package com.schneide.abas.ccd.green.tda.warmup.third;

public class Warmup3_Tell {

	public static void main(String[] args) {
		Road current = new Road("the long and winding");
		Road leftwards = new Road("to the left");
		Road rightwards = new Road("to the right");

		Pedestrian dave = new Pedestrian("Dave", false);
		current.stroll(dave);
		dave.walkEither(leftwards, rightwards);
	}

	private static class Road {
		private final String name;

		public Road(String name) {
			this.name = name;
		}

		public void stroll(Pedestrian driver) {
			System.out.println(driver + " walks " + this.name + " road");
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	private static class Pedestrian {
		private String name;
		private boolean tendsToTheLeft;

		public Pedestrian(String name, boolean tendsToTheLeft) {
			this.name = name;
			this.tendsToTheLeft = tendsToTheLeft;
		}

		public void walkEither(Road left, Road right) {
			if (this.tendsToTheLeft) {
				left.stroll(this);
				return;
			}
			right.stroll(this);
		}

		@Override
		public String toString() {
			return this.name;
		}
	}
}