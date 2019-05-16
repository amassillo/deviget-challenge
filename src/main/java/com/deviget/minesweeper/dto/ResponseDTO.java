package com.deviget.minesweeper.dto;

import lombok.Data;

/**
 * 
 * @author amassillo
 *
 */
@Data
public class ResponseDTO {
	private String[] messages;
	
	public ResponseDTO(String... pStringParams){
		this.setMessages(pStringParams);
	}
	
	public void setMessages(String... pStringParams) {
		this.messages = pStringParams;
	}
}
