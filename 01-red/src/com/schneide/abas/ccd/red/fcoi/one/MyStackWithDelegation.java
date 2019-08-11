package com.schneide.abas.ccd.red.fcoi.one;

import java.util.LinkedList;
import java.util.List;

public class MyStackWithDelegation<T> implements MyStack<T> {
	
	private static final int HEAD = 0;
	private final List<T> items;
	
	public MyStackWithDelegation() {
		super();
		this.items = new LinkedList<>();
	}
	
	@Override
	public void push(T item) {
		this.items.add(HEAD, item);
	}
	
	@Override
	public T pop() {
		return this.items.remove(HEAD);
	}
	
	@Override
	public int getSize() {
		return this.items.size();
	}
}
