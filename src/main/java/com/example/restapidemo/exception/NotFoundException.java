package com.example.restapidemo.exception;

/**
 * NotFoundException
 *
 * @author Daisuke Wakita
 */
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}
}
