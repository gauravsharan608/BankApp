/**
 * 
 */
package com.mindtree.exceptions;

/**
 * @author M1030608
 *
 */
public class InvalidInputException extends Exception {

	/**
	 * 
	 */
	public InvalidInputException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public InvalidInputException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public InvalidInputException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidInputException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidInputException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
