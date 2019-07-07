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

public class RefactoredAccountTest {

	@Test
	public void startsBalanceZero() {
		final Account account = newAccount();
		
		expectBalanceIsZeroFor(account);
	}

//	@Test
//	public void canDeposit() {
//		final Customer regular = mock(Customer.class);
//		when(regular.canOverdraw()).thenReturn(false);
//		final Account target = new Account(regular);
//
//		target.deposit(new Amount(new Euro(30)));
//
//		assertThat(target.balance()).isEqualTo(new Amount(new Euro(30)));
//	}

	@Test
	public void canDepositMultipleTimes() {
		final Account target = newAccount();

		depositEuros(10, target);
		depositEuros(10, target);
		depositEuros(10, target);

		expectBalanceIs(30, target);
	}

	@Test
	public void canDepositReallyOften() {
		final Account target = newAccount();
		
		for (int i = 0; i < 10000; i++) {
			depositEuros(1, target);
		}
		
		assertThat(target.balance()).isEqualTo(new Amount(new Euro(10000)));
	}

	@Test
	public void canWithdrawIfAvailable() {
		final Account target = newAccount();
		depositEuros(30, target);

		final Optional<Amount> payment = target.withdraw(new Amount(new Euro(30)));

		assertThat(payment).contains(new Amount(new Euro(30)));
		expectBalanceIsZeroFor(target);
	}

	@Test
	public void cannotWithdrawIfLess() {
		final Account target = newAccount();
		depositEuros(30, target);

		final Optional<Amount> payment = target.withdraw(new Amount(new Euro(50)));

		assertThat(payment).isEmpty();
		expectBalanceIs(30, target);
	}

	@Test
	public void goodCustomersCanOverdraw() {
		final Account target = createGoodAccount();
		depositEuros(30, target);

		final Optional<Amount> payment = target.withdraw(new Amount(new Euro(50)));

		assertThat(payment).contains(new Amount(new Euro(50)));
		assertThat(target.balance()).isEqualTo(new Amount(new Euro(-20)));
	}

	private void expectBalanceIs(final int amount, final Account target) {
		assertThat(target.balance()).isEqualTo(new Amount(new Euro(amount)));
	}

    private void depositEuros(int amountInEuros, final Account target) {
		target.deposit(new Amount(new Euro(amountInEuros)));
	}

	private void expectBalanceIsZeroFor(final Account target) {
		assertThat(target.balance()).isEqualTo(Amount.zero);
	}

	private Account createGoodAccount() {
		final Customer good = mock(Customer.class);
		when(good.canOverdraw()).thenReturn(true);
		final Account target = new Account(good);
		return target;
	}

	private Account newAccount() {
		final Customer regular = mock(Customer.class);
		when(regular.canOverdraw()).thenReturn(false);

		final Account target = new Account(regular);
		return target;
	}
}
