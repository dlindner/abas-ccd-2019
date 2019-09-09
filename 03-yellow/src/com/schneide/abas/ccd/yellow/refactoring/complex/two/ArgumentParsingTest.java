package com.schneide.abas.ccd.yellow.refactoring.complex.two;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import org.junit.Test;
import org.mockito.Mockito;

import com.schneide.abas.ccd.yellow.refactoring.complex.two.dependencies.CommandLine;
import com.schneide.abas.ccd.yellow.refactoring.complex.two.dependencies.InputParameters;

public class ArgumentParsingTest {

	@Test
	public void readsFromFile() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
					.with("i", "test.txt")
					.build());
		assertThat(actual.inputStream).isInstanceOf(DataInputStream.class);
	}

	@Test
	public void readsNotFromNotExistingFile() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
				.with("i", "test-not-existing.txt")
				.build());
		assertThat(actual).isNull();
	}

	@Test
	public void readsFromSystemInByDefault() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
					.build());
		assertThat(actual.inputStream).isEqualTo(System.in);
	}

	@Test
	public void writesToFile() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
					.with("o", "test-output.txt")
					.build());
		assertThat(actual.outputStream).isInstanceOf(DataOutputStream.class);
	}

	@Test
	public void writesToSystemOutByDefault() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
					.build());
		assertThat(actual.outputStream).isEqualTo(System.out);
	}

	@Test
	public void errorsToErrorFile() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
				.with("error", "test-error.txt")
				.build());
		assertThat(actual.errorOut).isInstanceOf(DataOutputStream.class);
	}

	@Test
	public void errorsToSystemErrByDefault() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
					.build());
		assertThat(actual.errorOut).isEqualTo(System.err);
	}

	@Test
	public void sourceEncoding() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
				.with("sourceEnc", "iso8859-1")
				.build());
		assertThat(actual.sourceEncoding).isEqualTo("iso8859-1");
	}

	@Test
	public void sourceEncodingUTF8ByDefault() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
				.build());
		assertThat(actual.sourceEncoding).isEqualTo("UTF-8");
	}

	@Test
	public void targetEncoding() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
				.with("targetEnc", "iso8859-1")
				.build());
		assertThat(actual.targetEncoding).isEqualTo("iso8859-1");
	}

	@Test
	public void targetEncodingUTF8ByDefault() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
				.build());
		assertThat(actual.targetEncoding).isEqualTo("UTF-8");
	}

	@Test
	public void maxRecordLoad() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
				.with("maxRecordLoad", "132")
				.build());
		assertThat(actual.maxRecordLoad).isEqualTo(132);
	}

	@Test
	public void maxRecordLoadNeverNegative() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
				.with("maxRecordLoad", "-132")
				.build());
		assertThat(actual.maxRecordLoad).isEqualTo(0);
	}

	@Test
	public void maxBufferSize() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
				.with("maxBufferSize", "1024")
				.build());
		assertThat(actual.maxBufferSize).isEqualTo(1_048_576);
	}

	@Test
	public void maxBufferSizeNeverNegative() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
				.with("maxBufferSize", "-128")
				.build());
		assertThat(actual.maxBufferSize).isEqualTo(0);
	}

	@Test
	public void maxBufferSizeCanBeZero() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
				.with("maxBufferSize", "0")
				.build());
		assertThat(actual.maxBufferSize).isEqualTo(0);
	}

	@Test
	public void stopOnError() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
				.with("stopOnError", "")
				.build());
		assertThat(actual.stopOnError).isTrue();
	}

	@Test
	public void stopOnErrorFalseByDefault() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
				.build());
		assertThat(actual.stopOnError).isFalse();
	}

	@Test
	public void webServiceURL() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
				.with("ws", "https://abas.de")
				.build());
		assertThat(target.getServiceURL()).isEqualTo("https://abas.de");
	}

	@Test
	public void webServiceURLByDefault() throws Exception {
		final ArgumentParsing target = new ArgumentParsing();
		final InputParameters actual = target.parse(
				commandLine()
				.build());
		assertThat(target.getServiceURL()).isEqualTo("defaultURL");
	}

	private CommandLineBuilder commandLine() {
		return new CommandLineBuilder();
	}

	private class CommandLineBuilder {

		private final CommandLine mock;

		public CommandLineBuilder() {
			super();
			this.mock = Mockito.mock(CommandLine.class);
		}

		public CommandLineBuilder with(String option, String value) {
			Mockito.when(this.mock.hasOption(option)).thenReturn(true);
			Mockito.when(this.mock.getOptionValue(option)).thenReturn(value);
			return this;
		}

		public CommandLine build() {
			return this.mock;
		}
	}
}
