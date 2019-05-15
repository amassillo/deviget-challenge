package com.deviget.minesweeper.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 
 * @author amassillo
 *
 */

@Entity
@Data
public class Cell {

	public enum CellStatus{
		CLEAR,
		BOMB,
		FLAGGED
	}
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private CellStatus status;
	
	private String flaggedWith;
	
	private Board board;
}
