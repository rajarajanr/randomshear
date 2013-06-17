package com.thoughtworks.sample.conf.track.api.exceptions;

/**
 * This class contains all the business logic related exceptions.
 * 
 */
public class ConfTrackerException extends Exception {

	private static final long serialVersionUID = -4916303570693874415L;

	public ConfTrackerException() {
		super();
	}

	public ConfTrackerException(String message) {
		super(message);
	}

	public ConfTrackerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfTrackerException(Throwable cause) {
		super(cause);
	}

}
