package com.deviget.minesweeper.service.algo;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.deviget.minesweeper.entity.Board;
import com.deviget.minesweeper.service.Cell;

/**
 * 
 * @author amassillo
 *
 */
public class BoardGenerator {

	public static Cell[][] generateBoard(Board pBoard, Integer pCols, 
			 				  			Integer pRows, Integer pNbrOfMines) {
		//generate mines location
		Hashtable<Integer, List<Integer>> lMines = new Hashtable<Integer, List<Integer>>();
		for (int i = 0; i< pNbrOfMines; i++) {
			Integer col = ThreadLocalRandom.current().nextInt(0, pCols);
			Integer row = ThreadLocalRandom.current().nextInt(0, pRows);
			List<Integer> lKeyCol = lMines.get(col);
			if (lKeyCol !=null) {
				if(lKeyCol.contains(row)) {
					//repeated mine, calculate again
					i--;
				}else{
					lKeyCol.add(row);
				}
			}else
				lMines.put(col, Arrays.asList(row));
		}
		//convert to array
		Cell[][] lCells = new Cell[pCols][pRows];
		//copy mined cells
		Enumeration<Integer> lColKeys = lMines.keys();
		while (lColKeys.hasMoreElements()) {
		  Integer element = lColKeys.nextElement();
		  for (Integer lRow : lMines.get(element))
			  lCells[element][lRow] = new Cell(Cell.CellStatus.BOMB, pBoard);
		}
		return lCells;
	}
}
