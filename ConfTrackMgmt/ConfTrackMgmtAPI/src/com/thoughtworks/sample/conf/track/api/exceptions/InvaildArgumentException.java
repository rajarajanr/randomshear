package com.thoughtworks.sample.conf.track.api.exceptions;

/**
 * This exception categorized the invalid input criteria.
 */
public class InvaildArgumentException extends ConfTrackerException {

	private static final long serialVersionUID = 6949336844525754058L;

	public InvaildArgumentException() {
		super();
	}

	public InvaildArgumentException(String message) {
		super(message);
	}

	public InvaildArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvaildArgumentException(Throwable cause) {
		super(cause);
	}

}
