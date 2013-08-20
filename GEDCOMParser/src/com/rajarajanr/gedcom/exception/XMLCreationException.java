package com.rajarajanr.gedcom.exception;

/**
 * <p>
 * <b>Title: </b>XMLCreationException.java
 * </p>
 * <p>
 * <b>Description: </b> Class to be used for exception scenarios while XML
 * generation
 * </p>
 * <p>
 * <b>@author Originator:</b> rrajendran
 * <p>
 * </p>
 * <br>
 * 
 */
public class XMLCreationException extends GEDCOMParserException {

	private static final long serialVersionUID = 1L;

	public XMLCreationException(String msg) {
		super(msg);
	}

	public XMLCreationException(String msg, Throwable e) {
		super(msg, e);
	}

}
