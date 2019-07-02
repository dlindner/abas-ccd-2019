package com.schneide.abas.ccd.red.refactoring.one;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;

import com.schneide.abas.ccd.red.refactoring.one.domain.Account;
import com.schneide.abas.ccd.red.refactoring.one.domain.Customer;
import com.schneide.abas.ccd.red.refactoring.one.domain.money.Amount;
import com.schneide.abas.ccd.red.refactoring.one.domain.money.Euro;

public class AccountTest {

	@Test
	public void startsEmpty() throws Exception {
		final Customer regular = mock(Customer.class);
		when(regular.canOverdraw()).thenReturn(false);

		final Account target = new Account(regular);

		assertThat(target.balance()).isEqualTo(Amount.zero);
	}

	@Test
	public void canDeposit() throws Exception {
		final Customer regular = mock(Customer.class);
		when(regular.canOverdraw()).thenReturn(false);
		final Account target = new Account(regular);

		target.deposit(new Amount(new Euro(30)));

		assertThat(target.balance()).isEqualTo(new Amount(new Euro(30)));
	}

	@Test
	public void canDepositMultipleTimes() throws Exception {
		final Customer regular = mock(Customer.class);
		when(regular.canOverdraw()).thenReturn(false);
		final Account target = new Account(regular);

		target.deposit(new Amount(new Euro(10)));
		target.deposit(new Amount(new Euro(10)));
		target.deposit(new Amount(new Euro(10)));

		assertThat(target.balance()).isEqualTo(new Amount(new Euro(30)));
	}

	@Test
	public void canWithdrawIfAvailable() throws Exception {
		final Customer regular = mock(Customer.class);
		when(regular.canOverdraw()).thenReturn(false);
		final Account target = new Account(regular);
		target.deposit(new Amount(new Euro(30)));

		final Optional<Amount> payment = target.withdraw(new Amount(new Euro(30)));

		assertThat(payment).contains(new Amount(new Euro(30)));
		assertThat(target.balance()).isEqualTo(Amount.zero);
	}

	@Test
	public void cannotWithdrawIfLess() throws Exception {
		final Customer regular = mock(Customer.class);
		when(regular.canOverdraw()).thenReturn(false);
		final Account target = new Account(regular);
		target.deposit(new Amount(new Euro(30)));

		final Optional<Amount> payment = target.withdraw(new Amount(new Euro(50)));

		assertThat(payment).isEmpty();
		assertThat(target.balance()).isEqualTo(new Amount(new Euro(30)));
	}

	@Test
	public void goodCustomersCanOverdraw() throws Exception {
		final Customer good = mock(Customer.class);
		when(good.canOverdraw()).thenReturn(true);
		final Account target = new Account(good);
		target.deposit(new Amount(new Euro(30)));

		final Optional<Amount> payment = target.withdraw(new Amount(new Euro(50)));

		assertThat(payment).contains(new Amount(new Euro(50)));
		assertThat(target.balance()).isEqualTo(new Amount(new Euro(-20)));
	}
}
