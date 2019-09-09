package com.schneide.abas.ccd.yellow.refactoring.complex.one.dependencies;

public interface Logger {

	boolean isInfoEnabled();

	void error(String string, Exception e1);

	void error(ImporterException e);

	void info(String string);

	void error(String string);

}
