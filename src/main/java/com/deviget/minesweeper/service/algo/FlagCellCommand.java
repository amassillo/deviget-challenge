package com.deviget.minesweeper.service.algo;

import com.deviget.minesweeper.entity.Board;
import com.deviget.minesweeper.service.Cell;

/**
 * 
 * @author amassillo
 *
 */
public class FlagCellCommand implements CellCommand {
	
	private Cell.CellFlag flagWith ;
	
	public FlagCellCommand(Cell.CellFlag pFlag) {
		flagWith = pFlag;
	}
	
	public boolean execute(Board pBoard, Integer pCol, Integer pRow) {
		Cell lCell = pBoard.getCells()[pCol][pRow]; 
		lCell.setFlaggedWith(flagWith);
		return true;
	}
}
