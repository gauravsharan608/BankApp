/**
 * 
 */
package com.mindtree.exceptions;

/**
 * @author M1030608
 *
 */
public class DaoException extends Exception {

	/**
	 * It defines default version of the class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Non-arg constructor to facilitate creating an object having default
	 * values of the properties
	 */
	public DaoException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 *            This constructor facilitates creating new objects with
	 *            attributes having some initial values.It takes message to be
	 *            displayed to the user as its argument and passes the message
	 *            to Exception class.
	 */
	public DaoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 *            This constructor takes as its parameter the message to be
	 *            displayed and cause of the exception as an instance of
	 *            Throwable class. It passes the message and cause to its super
	 *            class.
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DaoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
