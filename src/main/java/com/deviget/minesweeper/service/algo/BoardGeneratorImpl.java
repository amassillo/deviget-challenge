package com.deviget.minesweeper.service.algo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.deviget.minesweeper.entity.Board;
import com.deviget.minesweeper.entity.Cell;

import lombok.Data;

/**
 * 
 * @author amassillo
 */
public class BoardGeneratorImpl implements  BoardGenerator{

	private Integer cols;
	private Integer rows;
	@Data
	private class Position{
		public Integer x;
		public Integer y;
		
		public Position(Integer pX, Integer pY) {
			this.x = pX;
			this.y = pY;
		}
	}
	/**
	 * 
	 * @param pCols
	 * @param pRows
	 * @param pNbrOfMines
	 * @return
	 */
	public Board generateBoard(Integer pCols, Integer pRows, Integer pNbrOfMines) {

		this.cols = pCols;
		this.rows = pRows;
		Board lBoard = new Board();
		Cell[][] lCells = new Cell[pRows][pCols];
		List<Position> lPositions = new ArrayList<Position>();
		//generate mines location O(pNbrOfMines)
		for (int i = 0; i< pNbrOfMines; i++) {
			Integer col = ThreadLocalRandom.current().nextInt(0, pCols);
			Integer row = ThreadLocalRandom.current().nextInt(0, pRows);
			if (lCells[row][col] == null) {
				lCells[row][col] = new Cell(true);
				lPositions.add(new Position(row,col));
			}
			else 
				i--; //continue looping
		}
		for (Position lPosition : lPositions) {
			incrementBombCounter(lPosition.getX(), lPosition.getY(), lCells);
		}
		lBoard.setCells(lCells);
		lBoard.setRows(pRows);
		lBoard.setCols(pCols);
		lBoard.setMines(pNbrOfMines);
		LocalDateTime lTime = LocalDateTime.now();
		lBoard.setStartDateTime(lTime);
		lBoard.setLastDateTimeStarted(lTime);
		lBoard.setDuration(Duration.ZERO);
		return lBoard;
	}
	/**
	 * 
	 * @param pCol
	 * @param pRow
	 * @param pCells
	 */
	private void incrementBombCounter(int pRow, int pCol, Cell[][] pCells) {
		int pFromCol = pCol > 0 ? pCol -1 : pCol;
		int pToCol = pCol < this.cols -1 ? pCol + 1 : pCol;

		int pFromRow = pRow > 0 ? pRow -1 : pRow;
		int pToRow = pRow < this.rows -1 ? pRow + 1 : pRow;
		
		for (int i = pFromRow; i<= pToRow; i++)
			for (int j = pFromCol; j<= pToCol; j++) {
				if (pRow ==i && pCol == j) { ///it's me
					continue;
				}
				Cell lSibling = pCells[i][j];
				if (lSibling != null && lSibling.isHasBomb()) {
					continue;				//it's my sibling bomb, it'll do its own calc
				}
				if (lSibling == null) {
					lSibling = new Cell();
				}
				lSibling.setFlagValue(lSibling.getFlagValue() + 1);
				pCells[i][j] = lSibling;
			}
	}
}