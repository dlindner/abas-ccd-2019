package com.schneide.abas.ccd.red.fcoi;

public interface MyStack<ELEMENT> {

	public void push(ELEMENT item);

	public ELEMENT pop();

	public int items();
}
