package com.schneide.abas.ccd.yellow.refactoring.complex.two.dependencies;

public class Logger {

	public boolean isErrorEnabled() {
		return true;
	}

	public void error(String string) {
		System.err.println(string);
	}

	public boolean isDebugEnabled() {
		return true;
	}

	public void debug(String string) {
		System.out.println(string);
	}
}
