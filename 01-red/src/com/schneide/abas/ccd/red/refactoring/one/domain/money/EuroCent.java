package com.schneide.abas.ccd.red.refactoring.one.domain.money;

public class EuroCent {

	public static final EuroCent zero = new EuroCent(0);

	private final int amount;

	public EuroCent(int cent) {
		super();
		this.amount = cent;
	}

	public int amount() {
		return this.amount;
	}

	public EuroCent plus(EuroCent other) {
		return new EuroCent(amount() + other.amount());
	}

	public boolean isLessThan(EuroCent other) {
		return amount() < other.amount();
	}

	public EuroCent minus(EuroCent other) {
		return plus(other.inverse());
	}

	public EuroCent inverse() {
		return new EuroCent(-amount());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
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
		EuroCent other = (EuroCent) obj;
		if (amount != other.amount)
			return false;
		return true;
	}
}
