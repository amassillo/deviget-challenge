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
			//can't click a flagged cell
			EnumSet<CellFlag> lOptions = EnumSet.of(CellFlag.FLAG, CellFlag.QUESTION_MARK);
			if (lCell.getFlag()!=null && lOptions.contains(lCell.getFlag()))
				return false;
			//check siblings
			int pFromCol = pCol > 0 ? pCol -1 : pCol;
			int pToCol = pCol < this.lBoard.getCols() -1 ? pCol + 1 : pCol;

			int pFromRow = pRow > 0 ? pRow -1 : pRow;
			int pToRow = pRow < this.lBoard.getRows() -1 ? pRow + 1 : pRow;
			
			lCell.setClicked(true);
			//reveal only if no mines nearby
			if (lCell.getFlagValue() > 0)
				return true;
			for (int i = pFromRow; i<= pToRow; i++)
				for (int j = pFromCol; j<= pToCol; j++) {
					if (pRow ==i && pCol == j) { ///it's me
						continue;
					}
					Cell lSibling = lCells[i][j];
					if (lSibling == null) {
						lSibling = new Cell();
						lCells[i][j] = lSibling;
					}
					if (lSibling.isHasBomb() || lSibling.isClicked())
						continue; // not propagate
					lSibling.setClicked(true);
					//continue checking
					execute (j,i);
				}
			//clean up FLAGS if any
			lCells[pRow][pCol].setFlag(null);
			return true;
		}
		//bomb!
		return false;
	}
}
