package com.deviget.minesweeper.service.exception;

/**
 * 
 * @author amassillo
 *
 */
public class IndexOutOfBoardException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IndexOutOfBoardException() {
		super("Please provide a valid set of cords");
	}
}
