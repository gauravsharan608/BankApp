/**
 * 
 */
package com.mindtree.exceptions;

/**
 * @author M1030608
 *
 */
public class ServiceExcption extends Exception {

	/**
	 * 
	 */
	public ServiceExcption() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ServiceExcption(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ServiceExcption(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceExcption(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ServiceExcption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
