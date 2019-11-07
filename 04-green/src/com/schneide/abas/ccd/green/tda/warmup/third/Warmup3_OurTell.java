package com.schneide.abas.ccd.green.tda.warmup.third;

public class Warmup3_OurTell {

	public static void main(String[] args) {
		Road current = new Road("the long and winding");
		Road leftwards = new Road("to the left");
		Road rightwards = new Road("to the right");

		Pedestrian dave = new Pedestrian("Dave", false);
		current.stroll(dave);
		
		// fork in the road
		dave.chooseFrom(leftwards, rightwards);
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
		
		public void chooseFrom(Road left, Road right) {
			if (this.tendsToTheLeft) {
				left.stroll(this);
			} else {
				right.stroll(this);
			}
		}
//
//		public boolean tendsLeftwards() {
//			return this.tendsToTheLeft;
//		}

		@Override
		public String toString() {
			return this.name;
		}
	}
}