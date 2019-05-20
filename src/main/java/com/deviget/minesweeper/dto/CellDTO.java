package com.deviget.minesweeper.dto;


import lombok.Data;

/**
 * 
 * @author amassillo
 *
 */
@Data
public class CellDTO {

	private String value;
	
	public CellDTO() {}
	public CellDTO(String pValue) {this.value = pValue;}
}
