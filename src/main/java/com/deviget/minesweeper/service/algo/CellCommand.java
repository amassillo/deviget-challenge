package com.deviget.minesweeper.service.algo;


/**
 * 
 * @author amassillo
 * this can be done with a functional interface
 */
public interface CellCommand {
	
	public boolean execute(int pCol, int pRow);
}
