package com.language.exceptions;

@SuppressWarnings("serial")
public class ParsingException extends RuntimeException {

	public ParsingException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParsingException(String message) {
		super(message);
	}

	public ParsingException(Throwable cause) {
		super(cause);
	}

}
