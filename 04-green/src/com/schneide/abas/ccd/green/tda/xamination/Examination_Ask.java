package com.schneide.abas.ccd.green.tda.xamination;

import java.util.ArrayList;
import java.util.List;

public class Examination_Ask {

	public static void main(String[] args) {
		List<Year> alive = new ArrayList<>();
		for (int i = 1978; i < 2019; i++) {
			alive.add(new Year(i));
		}

		for (Year each : alive) {
			if (each.asInt() % 4 == 0) {
				System.out.println(each.asInt() + " was a leap-year!");
				continue;
			}
			System.out.println(each.asInt() + " was a normal year.");
		}
	}

	private static class Year {

		private int ac;

		public Year(int ac) {
			this.ac = ac;
		}

		public int asInt() {
			return ac;
		}
	}

}
