package com.deviget.minesweeper.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deviget.minesweeper.entity.Board;

/**
 * 
 * @author amassillo
 *
 */
@RestController
@RequestMapping("/game")
public class GameController {

	@PutMapping (value = "/{boardId}/flag")
	public ResponseEntity<Board> flagCell(@RequestParam(value="col") Integer pCol, 
										 @RequestParam(value="row") Integer pRow,
										 @RequestParam(value="flag") Integer pFlag){
		//TODO
		return null;
	}
	
	@PostMapping (value = "/{boardId}/fire")
	public ResponseEntity<Board> clickCell(@RequestParam(value="col") Integer pCol, 
										 @RequestParam(value="row") Integer pRow){
		//TODO
		return null;
	}
}
