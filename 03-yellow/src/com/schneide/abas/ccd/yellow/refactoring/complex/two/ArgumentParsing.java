package com.schneide.abas.ccd.yellow.refactoring.complex.two;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.schneide.abas.ccd.yellow.refactoring.complex.two.dependencies.CommandLine;
import com.schneide.abas.ccd.yellow.refactoring.complex.two.dependencies.InputParameters;
import com.schneide.abas.ccd.yellow.refactoring.complex.two.dependencies.Logger;

public class ArgumentParsing {

	private static final Logger LOGGER = new Logger();
	private static final String SERVICE_URL = "defaultURL";
	private String serviceURL;

	public InputParameters parse(CommandLine cmd) throws IOException {
		InputParameters ret = new InputParameters();

		/*
		 * '-i'
		 */
		if (cmd.hasOption("i")) {
			final String filename = cmd.getOptionValue("i");
			System.out.println(new File(filename).getAbsolutePath());
			if (!new File(filename).exists()) {
				if (LOGGER.isErrorEnabled()) {
					LOGGER.error("Input-file " + filename + " does not exist");
				}
				return null;
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Input-File: " + filename);
			}
			ret.inputStream = new DataInputStream(new FileInputStream(filename));
		} else {
			/*
			 * DEFAULT: use System.in for input
			 */
			ret.inputStream = System.in;
		}

		/*
		 * '-o'
		 */
		if (cmd.hasOption("o")) {
			final String filename = cmd.getOptionValue("o");
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Output-File: " + filename);
			}
			ret.outputStream = new DataOutputStream(new FileOutputStream(
					new File(filename)));
		} else {
			/* DEFAULT: use System.out for output */
			ret.outputStream = System.out;
		}

		/*
		 * '-error'
		 */
		if (cmd.hasOption("error")) {
			final String filename = cmd.getOptionValue("error");
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Error-File: " + filename);
			}
			ret.errorOut = new DataOutputStream(new FileOutputStream(new File(
					filename)));
		} else {
			/* DEFAULT: use System.out for output */
			ret.errorOut = System.err;
		}

		/*
		 * '-encin'
		 */
		if (cmd.hasOption("sourceEnc")) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("SourceEncoding: "
						+ cmd.getOptionValue("sourceEnc"));
			}
			ret.sourceEncoding = cmd.getOptionValue("sourceEnc");
		} else {
			ret.sourceEncoding = "UTF-8";
		}

		/*
		 * '-encout'
		 */
		if (cmd.hasOption("targetEnc")) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("TargetEncoding: "
						+ cmd.getOptionValue("targetEnc"));
			}
			ret.targetEncoding = cmd.getOptionValue("targetEnc");
		} else {
			ret.targetEncoding = "UTF-8";
		}
		ret.sourceFormat = cmd.getOptionValue("sourceFormat");
		ret.targetFormat = cmd.getOptionValue("targetFormat");

		if (cmd.hasOption("maxRecordLoad")) {
			final String maxRecordLoad = cmd.getOptionValue("maxRecordLoad");
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("MaxRecordLoad: " + maxRecordLoad);
			}
			final int load = Integer.parseInt(maxRecordLoad);
			if (load > 0) {
				ret.maxRecordLoad = load;
			}
		}
		if (cmd.hasOption("maxBufferSize")) {
			final String maxBufferSize = cmd.getOptionValue("maxBufferSize");
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("MaxBufferSize: " + maxBufferSize + " KB");
			}
			final int mbs = Integer.parseInt(maxBufferSize);
			if (mbs > -1) {
				ret.maxBufferSize = mbs * 1024;
			}
		}
		if (cmd.hasOption("stopOnError")) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("StopOnError: true");
			}
			ret.stopOnError = true;
		}

		if (cmd.hasOption("ws")) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Setting web service url to: "
						+ cmd.getOptionValue("ws"));
			}
			setServiceUrl(cmd.getOptionValue("ws"));

		} else {
			setServiceUrl(SERVICE_URL);
		}
		return ret;
	}

	private void setServiceUrl(String newURL) {
		this.serviceURL = newURL;
	}

	protected String getServiceURL() {
		return this.serviceURL;
	}
}
