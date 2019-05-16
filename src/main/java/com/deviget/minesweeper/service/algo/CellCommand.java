package com.deviget.minesweeper.service.algo;

import com.deviget.minesweeper.entity.Board;

/**
 * 
 * @author amassillo
 *
 */
public interface CellCommand {
	
	public boolean execute(Board pBoard, Integer pCol, Integer pRow);
}
