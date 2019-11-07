package com.schneide.abas.ccd.green.tda.xamination;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Examination_Tell {

	public static void main(String[] args) {
		IntStream.rangeClosed(1978, 2019)
		         .mapToObj(Year::new)
		         .forEach(year -> year.doIfLeapYear(
		        		 				number -> System.out.println(number + " was a leap-year!"),
		        		 				number -> System.out.println(number + " was a normal year.")));
	}

	private static class Year {

		private int ac;

		public Year(int ac) {
			this.ac = ac;
		}

		public void doIfLeapYear(
				Consumer<Integer> thenAction,
				Consumer<Integer> elseAction) {
			if (this.ac % 4 == 0) {
				thenAction.accept(this.ac);
				return;
			}
			elseAction.accept(this.ac);
		}
	}
}
