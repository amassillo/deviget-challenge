package com.deviget.minesweeper.dto;

import com.deviget.minesweeper.entity.Board.Status;

import lombok.Data;

/**
 * 
 * @author amassillo
 *
 */
@Data
public class BoardDTO extends ResponseDTO{

	private Long id;
	private CellDTO[][] cells;
	private Status status;
}
