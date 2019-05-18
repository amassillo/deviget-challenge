package com.deviget.minesweeper.service.exception;

import com.deviget.minesweeper.entity.Board.Status;

/**
 * 
 * @author amassillo
 *
 */
public class BoardStatusException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BoardStatusException(Status pBoardStatus, String pMessage) {
		super(pMessage);
	}
}
