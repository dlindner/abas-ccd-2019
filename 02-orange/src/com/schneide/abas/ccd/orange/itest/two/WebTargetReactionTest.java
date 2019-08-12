package com.schneide.abas.ccd.orange.itest.two;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Point;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.assertj.swing.launcher.ApplicationLauncher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import com.schneide.abas.ccd.orange.soc.one.ClickMeGame;

public class WebTargetReactionTest {

	@BeforeClass
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}

	private ClientAndServer mockService;
	private FrameFixture window;

	@Before
	public void setUp() {
		mockService = ClientAndServer.startClientAndServer(4567);

		ApplicationLauncher.application(WebBasedClickMeGame.class).withArgs("false").start();
		window = new FrameFixture("game frame");
		window.show();
	}

	private void setupRandomnessAnswers() {
		mockService.when(
				HttpRequest.request().withMethod("GET").withPath("/rng/100"))
	    				   .respond(HttpResponse.response().withStatusCode(200).withBody("91"));
		mockService.when(
				HttpRequest.request().withMethod("GET").withPath("/rng/50"))
						   .respond(HttpResponse.response().withStatusCode(200).withBody("22"));
		mockService.when(
				HttpRequest.request().withMethod("GET").withPath("/rng/299"))
						   .respond(HttpResponse.response().withStatusCode(200).withBody("94"));
		mockService.when(
				HttpRequest.request().withMethod("GET").withPath("/rng/373"))
						   .respond(HttpResponse.response().withStatusCode(200).withBody("323"));
		mockService.when(
				HttpRequest.request().withMethod("GET").withPath("/rng/1000"))
						   .respond(HttpResponse.response().withStatusCode(200).withBody("519"));
	}

	@Test
	public void scoresPointOnTargetClick() {
		setupRandomnessAnswers();
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

		mockService.stop();
	}
}
