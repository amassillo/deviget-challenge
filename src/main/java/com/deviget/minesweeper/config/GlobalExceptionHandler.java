package com.deviget.minesweeper.config;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.deviget.minesweeper.dto.ResponseDTO;
import com.deviget.minesweeper.service.exception.BoardStatusException;
import com.deviget.minesweeper.service.exception.IndexOutOfBoardException;
import com.deviget.minesweeper.service.exception.NotYourBoardException;
import com.deviget.minesweeper.service.exception.RecordNotFoundException;

/**
 * 
 * @author amassillo
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler  {
 
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ResponseDTO> handleSQLException(HttpServletRequest request, Exception ex){
		logger.info("SQLException Occured:: URL="+request.getRequestURL());
		return new ResponseEntity<ResponseDTO> (new ResponseDTO("An unexpected error has ocurred, please try again later"),
												HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<ResponseDTO> handleIOException(Exception ex){
		logger.error("IOException handler executed: "+ ex.getMessage());
		return new ResponseEntity<ResponseDTO> (new ResponseDTO("An unexpected error has ocurred, please try again later"),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ResponseDTO> handleEntityNotFoundException(RecordNotFoundException ex){
		logger.error("Entity not found execption: "+ ex.getMessage());
		return new ResponseEntity<ResponseDTO> (new ResponseDTO("Record "+ ex.getId()+" not found"),
												HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BoardStatusException.class)
	public ResponseEntity<ResponseDTO> handleBoardStatusException(BoardStatusException ex){
		logger.error("Not able to update board status: "+ ex.getMessage());
		return new ResponseEntity<ResponseDTO> (new ResponseDTO("Can't use this board, game has ended"),
												HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IndexOutOfBoardException.class)
	public ResponseEntity<ResponseDTO> handleIndexOutOfBoardException(IndexOutOfBoardException ex){
		logger.error("Not able to update board status: "+ ex.getMessage());
		return new ResponseEntity<ResponseDTO> (new ResponseDTO("Please provide valid indexes"),
												HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotYourBoardException.class)
	public ResponseEntity<ResponseDTO> handleINotYourBoardException(NotYourBoardException ex){
		logger.error("Attempt to use somebody's else board: "+ ex.getMessage());
		return new ResponseEntity<ResponseDTO> (new ResponseDTO("Board not found"),
												HttpStatus.BAD_REQUEST);
	}
}

