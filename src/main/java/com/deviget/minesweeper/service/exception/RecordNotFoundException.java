package com.deviget.minesweeper.service.exception;

/**
 * 
 * @author amassillo
 *
 */
public class RecordNotFoundException extends Exception {

	private Long id;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RecordNotFoundException(Long pId) {
		this.setId(pId);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
