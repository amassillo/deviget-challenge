package com.deviget.minesweeper.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 
 * @author amassillo
 *
 */
@Entity
@Table(name="game_user")
@Data
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String uname;
	
	//@OneToMany (fetch = FetchType.LAZY)
	//private List<Board> boards;
	
	private Integer score;
}
