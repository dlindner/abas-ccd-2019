//		final Random rng = new Random(132L) {
package com.schneide.abas.ccd.orange.itest.one;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.awt.Point;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.assertj.swing.launcher.ApplicationLauncher;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.schneide.abas.ccd.orange.itest.one.cut.TestedClickMeGame;

public class OurPinningTest {

//	@BeforeClass
//	public static void setUpOnce() {
//		FailOnThreadViolationRepaintManager.install();
//	}

	private FrameFixture window;

	@Before
	public void setUp() {
		ApplicationLauncher.application(TestedClickMeGame.class).withArgs("false").start();
		window = new FrameFixture("game frame");
		window.show();
	}
	
	@Test
	public void buttonClickGivesAPoint() throws Exception {
		window.label("points").requireText("0");
		JPanelFixture clickField = window.panel("game field");
		clickField.button("target-0").click();
		window.label("points").requireText("1");
	}
	
//	@Test
//	public void everyButtonGivesAPoint() throws Exception {
//		window.label("points").requireText("0");
//		JPanelFixture clickField = window.panel("game field");
//		for (int i = 0; i < 10; i++) {
//			clickField.button("target-" + i).click();
//			Thread.sleep(1000L);
//		}
//		window.label("points").requireText("10");
//	}
	
	@Test
	public void noPointForAMiss() throws Exception {
		window.label("points").requireText("0");
		JPanelFixture clickField = window.panel("game field");
		clickField.robot().click(clickField.target(), new Point(75, 75));
		window.label("points").requireText("0");
	}

	@After
	public void tearDown() {
		window.cleanUp();
	}
}
