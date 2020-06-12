package com.example.restapidemo.exception;

import org.springframework.validation.Errors;

import lombok.Getter;

/**
 * ValidationErrorException
 * 
 * 入力チェックでエラーがあった場合に投げるException
 *
 * @author daisuke-wakita
 */
@SuppressWarnings("serial")
public class ValidationErrorException extends RuntimeException {

	@Getter
	private final Errors errors;

	public ValidationErrorException(Errors errors) {
		super();
		this.errors = errors;
	}

}