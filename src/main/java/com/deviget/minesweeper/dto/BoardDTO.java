package com.deviget.minesweeper.dto;

import lombok.Data;

/**
 * 
 * @author amassillo
 *
 */
@Data
public class BoardDTO extends ResponseDTO{

	private Long id;
	//board settings
	private Integer cols;
	private Integer rows;
	private Integer mines;
	
}
