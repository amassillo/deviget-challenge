package com.deviget.minesweeper.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.deviget.minesweeper.service.Cell;

import lombok.Data;

/**
 * 
 * @author amassillo
 *
 */
@Entity
@Data
public class Board {

	@Id
	private Long id;
	//board settings
	private int cols;
	private int rows;
	private int mines;
	
	//board status
	public enum Status{
		NEW,
		RESUMED,
		COMPLETED //meaning user has won
	}
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	//store board as plain text (json) in a table column
	@Transient
	private Cell[][] cells;
	@Column(name = "plain_board")
	private String plainBoard;
	
	
	//timing references
	/**
	 * if instead of summing up the time spent in a game, 
	 * it's required to store each time user plays in a board, a new table is required (log table)
	*/  
	private LocalDate startDate;
	private Long duration;
	
	public Cell[][] getCells(){
		//from plain to array
		return this.cells;
	}
}
