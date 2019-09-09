package com.schneide.abas.ccd.yellow.refactoring.complex.one;

import com.schneide.abas.ccd.yellow.refactoring.complex.one.dependencies.Configuration;
import com.schneide.abas.ccd.yellow.refactoring.complex.one.dependencies.ErrorCode;
import com.schneide.abas.ccd.yellow.refactoring.complex.one.dependencies.FatalImporterException;
import com.schneide.abas.ccd.yellow.refactoring.complex.one.dependencies.ImportResponse;
import com.schneide.abas.ccd.yellow.refactoring.complex.one.dependencies.ImporterException;
import com.schneide.abas.ccd.yellow.refactoring.complex.one.dependencies.ImporterSystemException;
import com.schneide.abas.ccd.yellow.refactoring.complex.one.dependencies.Logger;
import com.schneide.abas.ccd.yellow.refactoring.complex.one.dependencies.NetpubAspect;
import com.schneide.abas.ccd.yellow.refactoring.complex.one.dependencies.Status;

public class Importer {

	private Logger logger;
	private ImportResponse response;
	private Configuration configuration;
	private NetpubAspect netpubAspect;

	public void doSomething() {
		long start = System.currentTimeMillis();

		try {

			netpubAspect = processChain(netpubAspect);

		} catch (final ImporterException e) {
			logger.error(e);
			if (e instanceof FatalImporterException) {
				try {
					doRollback(netpubAspect);
				} catch (Exception rollbacke) {
					logger.error("Rollback failure! - ", rollbacke);
				}
				try {
					delegateErrorReport(netpubAspect);
				} catch (final ImporterException e1) {

					if (logger.isInfoEnabled()) {
						logger.info("submitReport(Report report)-ERROR: "
								+ e1.getMessage());
					}
				}
				response.setStatus(Status.FAIL);
				response.setErrorCode(e.getErrorCode());
				response.setErrorMessage(e.getMessage());

			} else if (e instanceof ImporterSystemException) {

				// send ABORTED-Event
				try {
					sendWorkflowToolAbortedEvent(netpubAspect);
				} catch (final Exception e1) {
					logger.error("Could not send Aborted-Event!", e1);
				}
				try {
					doRollback(netpubAspect);
				} catch (Exception rollbacke) {
					logger.error("Rollback failure! - ", rollbacke);
				}
				response.setStatus(Status.ABORTED);
				response.setErrorCode(ErrorCode.SYSTEM_ERROR);
				response.setErrorMessage(e.getMessage());
			} else {
				// gedacht f�r MinorImporterException, ggf. Status erweitern
				// f�hrt zu keinem Rollback, wird aber per JIRA und durch den
				// Response-Status dokumentiert
				logger.error(" > run()" + ": catch Exception: "
						+ e.getMessage() + " - " + e.toString());
				response.setStatus(Status.FAIL);
				response.setErrorCode(e.getErrorCode());
				response.setErrorMessage(e.getMessage());
			}

			if (logger.isInfoEnabled()) {
				logger.info("<<<< unsuccessfull end (after "
						+ ((System.currentTimeMillis() - start) / 1000)
						+ "s) of importRequest: " + response.getRequest());
			}
			// Objekte freigeben
			netpubAspect = null;

			configuration.getPackageManager().closeRepository();
			configuration.getOldPackageManager().closeRepository();

			configuration.getImportServiceDao().saveImportResponse(response);

			return;
		}
	}

	private void delegateErrorReport(NetpubAspect netpubAspect2) throws ImporterException {
		// TODO Auto-generated method stub

	}

	private void doRollback(NetpubAspect netpubAspect2) {
		// TODO Auto-generated method stub

	}

	private void sendWorkflowToolAbortedEvent(NetpubAspect netpubAspect2) {
		// TODO Auto-generated method stub

	}

	private NetpubAspect processChain(NetpubAspect current) throws ImporterException {
		return current;
	}
}
