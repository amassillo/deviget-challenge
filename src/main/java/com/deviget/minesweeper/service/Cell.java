package com.deviget.minesweeper.service;


import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @author amassillo
 *
 */

@Data
public class Cell implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum CellStatus{
		CLEAR,
		BOMB	
	}
	public enum CellFlag{
		NUMBER, //not done by user
		QUESTION_MARK,
		FLAG	
	}
	private CellStatus status;
	
	private CellFlag flaggedWith;
	
	public Cell() {}
	
	public Cell(CellStatus pCellStatus) {
		this.setStatus(pCellStatus);
	}
}
