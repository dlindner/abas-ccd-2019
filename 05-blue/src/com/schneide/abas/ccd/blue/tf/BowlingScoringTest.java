package com.schneide.abas.ccd.blue.tf;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

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
	
	@Test
	public void oneHitWonder() {
		Game target = new Game("Jane");
		for (int i = 0; i < 19; i++) {
			target.roll(0);
		}
		target.roll(1);
		assertThat(target.score()).isEqualTo(1);
	}
	
	@Test
	public void normalGame() {
		Game target = new Game("Norman");
		for (int i = 0; i < 20; i++) {
			target.roll(5);
		}
		assertThat(target.score()).isEqualTo(100);
	}
	
	@Test
	public void perfectGame() {
		Game target = new Game("Wally");
		for (int i = 0; i < 12; i++) {
			target.roll(10);
		}
		assertThat(target.score()).isEqualTo(300);
		
	}

}
