package com.example.restapidemo.exception;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import lombok.Data;

@Data
public class ParameterError implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;

	private Map<String, String> errors = new HashMap<>();

	public ParameterError(ValidationErrorException exception) {
		message = exception.getMessage();
		Errors e = exception.getErrors();
		if (e != null) {
			List<FieldError> fieldErrors = e.getFieldErrors();
			if (!CollectionUtils.isEmpty(fieldErrors)) {
				for (FieldError fieldError : fieldErrors) {
					errors.put(fieldError.getField(), fieldError.getDefaultMessage());
				}
			}
		}
	}
}