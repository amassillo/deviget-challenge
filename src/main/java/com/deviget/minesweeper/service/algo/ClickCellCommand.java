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
public class ClickCellCommand implements CellCommand {
	
	private Board lBoard ;
	
	public ClickCellCommand(Board pBoard) {
		this.lBoard = pBoard;
	}
	
	public boolean execute(int pCol, int pRow) {
		Cell[][] lCells = lBoard.getCells();
		if (lCells[pRow][pCol] == null) {
			lCells[pRow][pCol] = new Cell();
		}
		Cell lCell = lCells[pRow][pCol];
		//already clicked this one
		if (lCell.isClicked())
			return true;
		//if no bomb, try to mark as clicked
		if(!lCell.isHasBomb()){
			lCell.setClicked(true);
			//can't click a flagged cell
			EnumSet<CellFlag> lOptions = EnumSet.of(CellFlag.FLAG, CellFlag.QUESTION_MARK);
			if (lCell.getFlag()!=null && lOptions.contains(lCell.getFlag()))
				return true;
			//check siblings
			int pFromCol = pCol > 0 ? pCol -1 : pCol;
			int pToCol = pCol < this.lBoard.getCols() -1 ? pCol + 1 : pCol;

			int pFromRow = pRow > 0 ? pRow -1 : pRow;
			int pToRow = pRow < this.lBoard.getRows() -1 ? pRow + 1 : pRow;
			
			for (int i = pFromRow; i<= pToRow; i++)
				for (int j = pFromCol; j<= pToCol; j++) {
					if (pRow ==i && pCol == j) { ///it's me
						continue;
					}
					Cell lSibling = lCells[i][j];
					if (lSibling == null) {
						lSibling = new Cell();
					}
					if (lSibling.isHasBomb())
						continue; // not propagate
					if (lSibling.getFlag().equals(CellFlag.NUMBER) && lSibling.getFlagValue() == 0) {
						lSibling.setClicked(true);
					}
					lCells[i][j] = lSibling;
					//continue checking
					execute (j,i);
				}

			return true;
		}
		//bomb!
		return false;
	}
}
