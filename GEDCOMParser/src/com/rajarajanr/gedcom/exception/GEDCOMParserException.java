package com.rajarajanr.gedcom.exception;

/**
 * <p>
 * <b>Title: </b>GEDCOMParserException.java
 * </p>
 * <p>
 * <b>Description: </b> Top Level Exception class for the GEDCOMParser
 * </p>
 * <p>
 * <b>@author Originator:</b> rrajendran
 * <p>
 * </p>
 * <br>
 * 
 */
public class GEDCOMParserException extends Exception {

	private static final long serialVersionUID = 1L;

	public GEDCOMParserException(String msg) {
		super(msg);
	}

	public GEDCOMParserException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
