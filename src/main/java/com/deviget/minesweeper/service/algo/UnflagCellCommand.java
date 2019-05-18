package com.deviget.minesweeper.service.algo;

import java.util.EnumSet;

import com.deviget.minesweeper.entity.Board;
import com.deviget.minesweeper.entity.Cell;
import com.deviget.minesweeper.entity.Cell.CellFlag;

/**
 * 
 * @author amassillo
 *
 */
public class UnflagCellCommand implements CellCommand {
	
	private Board board ;
	
	public UnflagCellCommand(Board pBoard) {
		this.board = pBoard;
	}
	
	public boolean execute(int pCol, int pRow) {
		Cell lCell = board.getCells()[pCol][pRow];
		//not able to flag numbered cells
		EnumSet<CellFlag> lOptions = EnumSet.of(CellFlag.FLAG, CellFlag.QUESTION_MARK);
		if (lCell !=null && lCell.getFlag() !=null &&
				lOptions.contains(lCell.getFlag())) {
				lCell.setFlag(null);
				return true;
			}
		return false;
	}
}
