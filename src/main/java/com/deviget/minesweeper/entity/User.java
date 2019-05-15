package com.deviget.minesweeper.entity;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;

/**
 * 
 * @author amassillo
 *
 */
@Entity
@Data
public class User {
	
	private List<Board> boards;
	private String uname;
	private Integer score;
}
