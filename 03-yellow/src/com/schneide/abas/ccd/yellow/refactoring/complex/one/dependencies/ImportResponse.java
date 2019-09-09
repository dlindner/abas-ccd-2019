package com.schneide.abas.ccd.yellow.refactoring.complex.one.dependencies;

public interface ImportResponse {

	String getRequest();

	void setStatus(Status fail);

	void setErrorCode(ErrorCode errorCode);

	void setErrorMessage(String message);

}
