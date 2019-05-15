package com.deviget.minesweeper.service.algo;

import com.deviget.minesweeper.entity.Board;

/**
 * 
 * @author amassillo
 *
 */
public interface ClickCellStrategy {
	
	public Board clickCell(Board pBoard, Integer pCol, Integer pRow);
}
