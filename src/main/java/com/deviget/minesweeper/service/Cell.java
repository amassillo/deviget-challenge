package com.deviget.minesweeper.service;

import com.deviget.minesweeper.entity.Board;

import lombok.Data;

/**
 * 
 * @author amassillo
 *
 */

@Data
public class Cell {

	public enum CellStatus{
		CLEAR,
		BOMB,
		QUESTION_MARK,
		RED_FLAG
	}
	private CellStatus status;
	
	private String flaggedWith;
	
	private Board board;
	
	public Cell() {}
	
	public Cell(CellStatus pCellStatus, Board pBoard) {
		this.setStatus(pCellStatus);
		this.setBoard(pBoard);
	}
}
