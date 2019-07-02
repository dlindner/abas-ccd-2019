package com.schneide.abas.ccd.red.refactoring.one.domain;

import java.util.Optional;

import com.schneide.abas.ccd.red.refactoring.one.domain.money.Amount;

public class Account {

	private final Customer owner;
	private volatile Amount balance;

	public Account(Customer owner) {
		super();
		this.owner = owner;
		this.balance = Amount.zero;
	}

	public void deposit(Amount addition) {
		this.balance = this.balance.plus(addition);
	}

	public Optional<Amount> withdraw(Amount requested) {
		if (!this.owner.canOverdraw()
				&& this.balance.isLessThan(requested)) {
			return Optional.empty();
		}
		this.balance = this.balance.minus(requested);
		return Optional.of(requested);
	}

	public Amount balance() {
		return this.balance;
	}
}
