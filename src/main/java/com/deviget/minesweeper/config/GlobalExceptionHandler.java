package com.deviget.minesweeper.config;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.deviget.minesweeper.dto.ResponseDTO;
import com.deviget.minesweeper.service.exception.BoardStatusException;
import com.deviget.minesweeper.service.exception.IndexOutOfBoardException;

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
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex){
		logger.error("Entity not found execption: "+ ex.getMessage());
		return new ResponseEntity<ResponseDTO> (new ResponseDTO("Record not found"),
												HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BoardStatusException.class)
	public ResponseEntity<ResponseDTO> handleBoardStatusException(BoardStatusException ex){
		logger.error("Not able to update board status: "+ ex.getMessage());
		return new ResponseEntity<ResponseDTO> (new ResponseDTO("Can't flag a cell of this board, game has ended"),
												HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IndexOutOfBoardException.class)
	public ResponseEntity<ResponseDTO> handleIndexOutOfBoardException(IndexOutOfBoardException ex){
		logger.error("Not able to update board status: "+ ex.getMessage());
		return new ResponseEntity<ResponseDTO> (new ResponseDTO("Please provide valid indexes"),
												HttpStatus.BAD_REQUEST);
	}
}

