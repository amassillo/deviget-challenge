package com.deviget.minesweeper.dto;

import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author amassillo
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ResponseIdsDTO extends ResponseDTO{

	private List<Long> ids;
	
	public ResponseIdsDTO() {}
	public ResponseIdsDTO(Long... pIds) {
		this.ids = Arrays.asList(pIds);
	}
	public ResponseIdsDTO(List<Long> pIds) {
		this.ids = pIds;
	}
}
