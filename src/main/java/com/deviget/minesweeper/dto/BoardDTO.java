package com.deviget.minesweeper.dto;

import java.time.Duration;
import java.time.LocalDateTime;

import com.deviget.minesweeper.entity.Board.Status;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author amassillo
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BoardDTO extends ResponseDTO{

	private Long id;
	private CellDTO[][] cells;
	private Status status;
	
	private LocalDateTime lastDateTimeStarted;
	private Duration duration;
}
