package com.deviget.minesweeper.service.algo;

import com.deviget.minesweeper.entity.Board;
import com.deviget.minesweeper.service.Cell;

/**
 * 
 * @author amassillo
 *
 */
public class ClickCellStrategyImpl implements ClickCellStrategy {
	
	public Board clickCell(Board pBoard, Integer pCol, Integer pRow) {
		Cell.CellStatus lCellStatus = pBoard.getCells()[pCol][pRow].getStatus(); 
		switch (lCellStatus){
			case BOMB: {
				//thows exception
			}
			default:{
				//reveal for siblings
				int pFromCol = pCol > 0 ? pCol -1 : pCol;
				int pToCol = pCol < pBoard.getCols() ? pCol + 1 : pCol;

				int pFromRow = pRow > 0 ? pRow -1 : pRow;
				int pToRow = pRow < pBoard.getRows() ? pRow + 1 : pRow;
				for (int i = pFromCol; i<pToCol; i++)
					for (int j = pFromRow; j<pToRow; j++)
						clickCell(pBoard, i,j);	
			}			
		}
		return null;
	}
}
