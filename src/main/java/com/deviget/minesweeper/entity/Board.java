package com.deviget.minesweeper.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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
	
	private User user;
	private String minedCells;//TODO
	
	//timing references
	private LocalDate startDate;
	private Long duration;
}
