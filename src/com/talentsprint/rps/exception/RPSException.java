package com.talentsprint.rps.exception;

public class RPSException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public RPSException(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "RPSException [message=" + message + "]";
	}

}
