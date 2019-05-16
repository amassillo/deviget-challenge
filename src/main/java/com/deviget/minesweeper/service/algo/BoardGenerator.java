package com.deviget.minesweeper.service.algo;


import com.deviget.minesweeper.entity.Board;

/**
 * 
 * @author amassillo
 * this might be turned into a functional interface
 * some other algoritm to generate the board can be created
 */
public interface BoardGenerator {
	/**
	 * 
	 * @param pCols
	 * @param pRows
	 * @param pNbrOfMines
	 * @return
	 */
	public Board generateBoard(Integer pCols, Integer pRows, Integer pNbrOfMines);
}