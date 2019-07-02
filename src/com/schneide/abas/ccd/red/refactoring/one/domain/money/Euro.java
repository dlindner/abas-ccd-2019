package com.schneide.abas.ccd.red.refactoring.one.domain.money;

public class Euro {

	public static final Euro zero = new Euro(0);

	private static final int asCents = 100;

	private final int amount;

	public Euro(int euro) {
		super();
		this.amount = euro;
	}

	public EuroCent inCent() {
		return new EuroCent(this.amount * asCents);
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
		Euro other = (Euro) obj;
		if (amount != other.amount)
			return false;
		return true;
	}
}
