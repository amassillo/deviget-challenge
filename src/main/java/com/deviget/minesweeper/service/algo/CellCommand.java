package com.deviget.minesweeper.service.algo;


/**
 * 
 * @author amassillo
 *
 */
public interface CellCommand {
	
	public boolean execute(int pCol, int pRow);
}
