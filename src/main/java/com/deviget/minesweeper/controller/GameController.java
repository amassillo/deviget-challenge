package com.deviget.minesweeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deviget.minesweeper.entity.Board;
import com.deviget.minesweeper.service.BoardService;

/**
 * 
 * @author amassillo
 *
 */
@RestController
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	private BoardService service;

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
