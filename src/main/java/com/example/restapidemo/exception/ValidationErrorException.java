package com.example.restapidemo.exception;

import org.springframework.validation.Errors;

/**
 * ValidationErrorException
 * 
 * 入力チェックでエラーがあった場合に投げるException
 *
 * @author daisuke-wakita
 */
@SuppressWarnings("serial")
public class ValidationErrorException extends RuntimeException {

	private Errors errors;

	public ValidationErrorException() {
		super();
	}

	public ValidationErrorException(String message) {
		super(message);
	}

	public ValidationErrorException(Errors errors, String message) {
		super(message);
		this.errors = errors;
	}

	public ValidationErrorException(Errors errors) {
		super();
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}
}