package com.schneide.abas.ccd.red.fcoi.one;

import java.util.ArrayList;

public class MyStackBasedOnArrayList<T> extends ArrayList<T> implements MyStack<T> {

	private static final long serialVersionUID = -2457926674569871821L;

	public MyStackBasedOnArrayList() {
		super();
	}

	@Override
	public void push(T item) {
		add(0, item);
	}

	@Override
	public T pop() {
		return remove(0);
	}

	@Override
	public int items() {
		return size();
	}
}
