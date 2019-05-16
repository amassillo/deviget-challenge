package com.deviget.minesweeper.service.algo;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import com.deviget.minesweeper.entity.Board;
import com.deviget.minesweeper.entity.Board.Status;
import com.deviget.minesweeper.service.Cell;

/**
 * 
 * @author amassillo
 */
public class BoardGeneratorImpl implements  BoardGenerator{

	/**
	 * 
	 * @param pCols
	 * @param pRows
	 * @param pNbrOfMines
	 * @return
	 */
	public Board generateBoard(Integer pCols, Integer pRows, Integer pNbrOfMines) {

		Board lBoard = new Board();
		Cell[][] lCells = new Cell[pCols][pRows];
		//generate mines location O(pNbrOfMines)
		for (int i = 0; i< pNbrOfMines; i++) {
			Integer col = ThreadLocalRandom.current().nextInt(0, pCols);
			Integer row = ThreadLocalRandom.current().nextInt(0, pRows);
			if (lCells[col][row] == null)
				lCells[col][row] = new Cell(Cell.CellStatus.BOMB);
			else 
				i--; //continue looping
			
		}
		lBoard.setCells(lCells);
		lBoard.setStatus(Status.NEW);
		lBoard.setRows(pRows);
		lBoard.setCols(pCols);
		lBoard.setMines(pNbrOfMines);
		lBoard.setStartDate(LocalDate.now());
		return lBoard;
	}
}