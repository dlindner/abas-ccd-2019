package com.schneide.abas.ccd.green.tda.xamination;

import java.util.ArrayList;
import java.util.List;

public class Examination_OurTell {

	public static void main(String[] args) {
		List<Year> alive = new ArrayList<>();
		for (int i = 1978; i < 2019; i++) {
			alive.add(new Year(i));
		}

		for (Year each : alive) {
			each.printLeapType();
		}
	}

	private static class Year {

		private int currentEra;

		public Year(int currentEra) {
			this.currentEra = currentEra;
		}
		
		public void printLeapType() {
			if (this.currentEra % 4 == 0) {
				System.out.println(this.currentEra + " was a leap-year!");
			} else {
				System.out.println(this.currentEra + " was a normal year.");
			}
		}
//
//		public int asInt() {
//			return ac;
//		}
	}

}
