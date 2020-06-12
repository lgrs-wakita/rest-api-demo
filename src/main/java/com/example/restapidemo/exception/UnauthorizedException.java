package com.example.restapidemo.exception;

/**
 * UnauthorizedException
 * 
 * ログインしていない場合に投げるException
 *
 * @author daisuke-wakita
 */
@SuppressWarnings("serial")
public class UnauthorizedException extends RuntimeException {

	public UnauthorizedException() {
		super();
	}

	public UnauthorizedException(String message) {
		super(message);
	}

	public UnauthorizedException(Exception e) {
		super(e);
	}

}