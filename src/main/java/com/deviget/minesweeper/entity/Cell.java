package com.deviget.minesweeper.entity;


import java.io.Serializable;


import lombok.Data;

/**
 * 
 * @author amassillo
 * Not an entity
 */

@Data
public class Cell implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum CellFlag{
		QUESTION_MARK,
		FLAG
	}
	private boolean hasBomb;
	
	private boolean clicked;
	private CellFlag flag;
	
	private Integer flagValue;
	
	/**
	 * default constructor
	 */
	public Cell() {
		//not clicked and no bombs around
		this(false,null,0);
	}
	
	public Cell(Boolean pCellStatus) {
		this.setHasBomb(pCellStatus);
	}
	/**
	 * 
	 * @param pCellStatus
	 * @param pFlag
	 * @param pValue
	 */
	public Cell(Boolean pCellStatus, CellFlag pFlag, Integer pValue) {
		this.setHasBomb(pCellStatus);
		this.setFlag(pFlag);
		this.setFlagValue(pValue);
	}
	
}
