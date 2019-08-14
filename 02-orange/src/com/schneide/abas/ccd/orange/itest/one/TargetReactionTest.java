package com.schneide.abas.ccd.orange.itest.one;

import static org.assertj.core.api.Assertions.assertThat;

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

public class TargetReactionTest {

	@BeforeClass
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}

	private FrameFixture window;

	@Before
	public void setUp() {
		ApplicationLauncher.application(TestedClickMeGame.class).withArgs("false").start();
		window = new FrameFixture("game frame");
		window.show();
	}

	@Test
	public void scoresPointOnTargetClick() {
		JPanelFixture clickField = window.panel("game field");
		window.label("points").requireText("0");
		clickField.robot().click(
				clickField.target(),
				new Point(115, 325));
		window.label("points").requireText("1");
	}

	@Test
	public void targetDisappearsIfHit() {
		JPanelFixture clickField = window.panel("game field");
		assertThat(clickField.target().getComponentCount()).isEqualTo(1);
		clickField.robot().click(
				clickField.target(),
				new Point(115, 325));
		assertThat(clickField.target().getComponentCount()).isEqualTo(0);
	}

	@Test
	public void targetCountAdjusts() {
		JPanelFixture clickField = window.panel("game field");
		window.label("targets").requireText("1");
		clickField.robot().click(
				clickField.target(),
				new Point(115, 325));
		window.label("targets").requireText("0");
	}

	@After
	public void tearDown() {
		window.cleanUp();
	}
}
