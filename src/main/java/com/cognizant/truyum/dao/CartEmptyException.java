package com.cognizant.truyum.dao;

public class CartEmptyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1527380878700425214L;
	/**
	 * 
	 */
	public CartEmptyException() {
		super();
		// TODO Auto-generated constructor stub
	}
	 public CartEmptyException(final String message) {
	        super(message);
	    }

}