package com.schneide.abas.ccd.blue.tf;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class BowlingScoringTest {

	@Test
	public void worstGameEver() {
		Game target = new Game("Joe");
		for (int i = 0; i < 20; i++) {
			target.roll(0);
		}
		assertThat(target.score()).isEqualTo(0);
	}
}
