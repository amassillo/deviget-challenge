package com.deviget.minesweeper.service.algo;

import com.deviget.minesweeper.entity.Board;
import com.deviget.minesweeper.entity.Cell;
import com.deviget.minesweeper.entity.Cell.CellFlag;

/**
 * 
 * @author amassillo
 *
 */
public class FlagCellCommand implements CellCommand {
	
	private CellFlag flag ;
	private Board board ;
	
	public FlagCellCommand(Board pBoard, CellFlag pFlag) {
		this.flag = pFlag;
		this.board = pBoard;
	}
	
	public boolean execute(int pCol, int pRow) {
		Cell lCell = board.getCells()[pCol][pRow];
		if (lCell == null) {
			lCell = new Cell();
			board.getCells()[pCol][pRow] = lCell;
		}
		//not able to flag numbered cells
		if (lCell.getFlag() !=null && lCell.getFlag().equals(CellFlag.NUMBER) && lCell.getFlagValue()>0)
			return false;
		lCell.setFlag(flag);
		return true;
	}
}