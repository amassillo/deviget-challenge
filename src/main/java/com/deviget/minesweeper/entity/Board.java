package com.deviget.minesweeper.entity;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Proxy;

import com.deviget.minesweeper.entity.converter.CellAttributeConverter;

import lombok.Data;

/**
 * 
 * @author amassillo
 *
 */
@Entity
@Data
@Proxy(lazy = false)
public class Board {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	//board settings
	private Integer cols;
	private Integer rows;
	private Integer mines;
	
	//board status
	public enum Status{
		NEW,
		ON_GOING,
		PAUSED,
		FINALIZED, //meaning game has ended
	}
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Status status;
	
	private boolean result; //user won or not (true or false)
	private Long userId;
	
	//store board as plain text (json) in a table column
	@Convert(converter = CellAttributeConverter.class)
	private Cell[][] cells;
	
	
	//timing references
	/**
	 * if instead of summing up the time spent in a game, 
	 * it's required to store each time user plays in a board, a new table is required (log table)
	*/  
	private LocalDateTime startDateTime;
	
	private LocalDateTime lastDateTimeStarted;
	private Duration duration;
	
}
