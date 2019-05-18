package com.deviget.minesweeper.dto;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

/**
 * 
 * @author amassillo
 *
 */
@Data
public class ResponseIdsDTO extends ResponseDTO{

	private List<Long> boardIds;
	
	public ResponseIdsDTO() {}
	public ResponseIdsDTO(Long... pIds) {
		this.boardIds = Arrays.asList(pIds);
	}
	public ResponseIdsDTO(List<Long> pIds) {
		this.boardIds = pIds;
	}
}
