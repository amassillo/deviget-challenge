package com.deviget.minesweeper.entity;

import java.util.List;

import javax.persistence.Entity;
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
public class User {
	@Id
	private Long id;
	@NotNull
	private String uname;
	
	private List<Board> boards;
	
	private Integer score;
}
