package com.example.restapidemo.exception.handler;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.restapidemo.exception.BadRequestException;
import com.example.restapidemo.exception.NotFoundException;
import com.example.restapidemo.exception.ParameterError;
import com.example.restapidemo.exception.UnauthorizedException;
import com.example.restapidemo.exception.ValidationErrorException;

import lombok.extern.slf4j.Slf4j;

/**
 * ExceptionHandler
 *
 * @author Daisuke Wakita
 */
/**
 * ExceptionHandler
 *
 * @author Daisuke Wakita
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandler {

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@org.springframework.web.bind.annotation.ExceptionHandler({ UnauthorizedException.class })
	@ResponseBody
	public void handle(UnauthorizedException e) {
		log.trace("{}", e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler({ BadRequestException.class })
	@ResponseBody
	public void handle(BadRequestException e) {
		log.info("{}", e);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@org.springframework.web.bind.annotation.ExceptionHandler({ NotFoundException.class })
	@ResponseBody
	public void handle(NotFoundException e) {
		log.info("{}", e);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@org.springframework.web.bind.annotation.ExceptionHandler({ NoSuchElementException.class })
	@ResponseBody
	public void handle(NoSuchElementException e) {
		log.info("{}", e);
	}

	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@org.springframework.web.bind.annotation.ExceptionHandler({ ValidationErrorException.class })
	@ResponseBody
	public ParameterError handle(ValidationErrorException e) {
		log.info("{}", e.getErrors());
		return new ParameterError(e);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler({ RuntimeException.class })
	@ResponseBody
	public void handle(RuntimeException e) {
		log.error("{}", e);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler({ Exception.class })
	@ResponseBody
	public void handle(Exception e) {
		log.error("{}", e);
	}

}