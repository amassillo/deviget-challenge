package com.deviget.minesweeper.service.algo;

import com.deviget.minesweeper.entity.Board;
import com.deviget.minesweeper.service.Cell;

/**
 * 
 * @author amassillo
 *
 */
public class ClickCellCommand implements CellCommand {
	
	public boolean execute(Board pBoard, Integer pCol, Integer pRow) {
		Cell lCell = pBoard.getCells()[pCol][pRow];
		//can't click a flagged cell
		if (lCell.getFlaggedWith() !=null)
			return false;
		//otherwise proceed to check cell content
		Cell.CellStatus lCellStatus = lCell.getStatus(); 
		switch (lCellStatus){
			case BOMB: {
				//throws exception
				return false;
			}
			case CLEAR:{
				//reveal siblings when possible, calculate siblings bombs #
				int pFromCol = pCol > 0 ? pCol -1 : pCol;
				int pToCol = pCol < pBoard.getCols() ? pCol + 1 : pCol;

				int pFromRow = pRow > 0 ? pRow -1 : pRow;
				int pToRow = pRow < pBoard.getRows() ? pRow + 1 : pRow;
				for (int i = pFromCol; i<pToCol; i++)
					for (int j = pFromRow; j<pToRow; j++)
						return execute(pBoard, i,j); //propagate	
			}
		}
		return true;
	}
}
