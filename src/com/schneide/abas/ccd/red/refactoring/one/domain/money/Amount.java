package com.schneide.abas.ccd.red.refactoring.one.domain.money;

public class Amount {

	public static final Amount zero = new Amount(EuroCent.zero);

	private final EuroCent cents;

	public Amount(EuroCent cents) {
		this(Euro.zero, cents);
	}

	public Amount(Euro euros) {
		this(euros, EuroCent.zero);
	}

	public Amount(Euro euros, EuroCent cents) {
		super();
		this.cents = cents.plus(euros.inCent());
	}

	public boolean isLessThan(Amount other) {
		return this.cents.isLessThan(other.cents);
	}

	public Amount plus(Amount other) {
		return new Amount(this.cents.plus(other.cents));
	}

	public Amount minus(Amount other) {
		return new Amount(this.cents.minus(other.cents));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cents == null) ? 0 : cents.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Amount other = (Amount) obj;
		if (cents == null) {
			if (other.cents != null)
				return false;
		} else if (!cents.equals(other.cents))
			return false;
		return true;
	}
}
