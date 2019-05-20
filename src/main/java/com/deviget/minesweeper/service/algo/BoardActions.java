package com.deviget.minesweeper.service.algo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.deviget.minesweeper.entity.Board;
import com.deviget.minesweeper.entity.Cell;

import lombok.Data;

/**
 * 
 * @author amassillo
 *
 */
@Data
@Component
public class BoardActions {
	
	private List<CellCommand> actions;
	
	
	public BoardActions() {
		this.actions = new ArrayList<CellCommand>();
	}
	/**
	 * 
	 * @param pCommand
	 */
	//TODO remove row and col params for command to rely in inner data only
	private boolean addAction(CellCommand pCommand, Integer pCol, Integer pRow) {
		boolean lResult = pCommand.execute(pCol, pRow);
		if (lResult)
			this.actions.add(pCommand);
		return lResult;
	}
	
	public boolean click(Board pBoard, Integer pCol, Integer pRow) {
		CellCommand lCommand = new ClickCellCommand(pBoard);
		return this.addAction(lCommand, pCol-1, pRow-1);
	}

	public boolean flag(Board pBoard, Cell.CellFlag pFlag, Integer pCol, Integer pRow) {
		CellCommand lCommand = new FlagCellCommand(pBoard,pFlag);
		return this.addAction(lCommand, pCol-1, pRow-1);
	}

	public boolean unflag(Board pBoard,Integer pCol, Integer pRow) {
		CellCommand lCommand = new UnflagCellCommand(pBoard);
		return this.addAction(lCommand, pCol-1, pRow-1);
	}
}
