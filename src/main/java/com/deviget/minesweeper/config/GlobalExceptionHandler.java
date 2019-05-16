package com.deviget.minesweeper.config;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author amassillo
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler  {
 
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(SQLException.class)
	public String handleSQLException(HttpServletRequest request, Exception ex){
		logger.info("SQLException Occured:: URL="+request.getRequestURL());
		return "database_error";
	}
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="IOException occured")
	@ExceptionHandler(IOException.class)
	public void handleIOException(Exception ex){
		logger.error("IOException handler executed: "+ ex.getMessage());
		//returning 404 error code
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Entity not found")
	@ExceptionHandler(EntityNotFoundException.class)
	public void handleEntityNotFoundException(Exception ex){
		logger.error("Entity not found execption: "+ ex.getMessage());
		//returning 404 error code
	}

}

