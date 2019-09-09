package com.schneide.abas.ccd.yellow.refactoring.complex.two.dependencies;

import java.io.InputStream;
import java.io.OutputStream;

public class InputParameters {

	public InputStream inputStream;
	public OutputStream outputStream;
	public OutputStream errorOut;
	public String sourceEncoding;
	public String targetEncoding;
	public String sourceFormat;
	public String targetFormat;
	public int maxRecordLoad;
	public int maxBufferSize;
	public boolean stopOnError;
}
