package com.deviget.minesweeper.service.algo;

import java.util.List;

import com.deviget.minesweeper.entity.Board;

import lombok.Data;

/**
 * 
 * @author amassillo
 *
 */
@Data
public class BoardActions {
	
	private Board board;
	private List<CellCommand> actions;
	private Long time;
	
	
	public BoardActions(Board pBoard) {
		this.board = pBoard;
	}
	public void addAction(CellCommand pCommand) {
		//pCommand.execute();
		this.actions.add(pCommand);
	}
}
