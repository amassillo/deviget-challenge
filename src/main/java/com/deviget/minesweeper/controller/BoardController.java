package com.deviget.minesweeper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;

	@GetMapping (value = "/")
	public ResponseEntity<List<Board>> getBoards(@RequestParam(value="user_id") Long pUserId){
		try {
			return new ResponseEntity<List<Board>> (service.getUserBoards(pUserId),HttpStatus.OK);
		}catch (Exception e) {
			//other unexpected error
			return new ResponseEntity<List<Board>> (HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping (value = "/{userId}")
	public ResponseEntity<Board> newGame(@RequestParam(value="cols") Integer pCols, 
											   @RequestParam(value="rows") Integer pRows,
											   @RequestParam(value="mines") Integer pNbrOfMines){
		try {
			return new ResponseEntity<Board> (service.startNewGame(1L, pCols, pRows, pNbrOfMines),HttpStatus.OK);
		}catch (Exception e) {
			//other unexpected error
			return new ResponseEntity<Board> (HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping (value = "/{userId}")
	public ResponseEntity<Board> resumeBoard(@RequestParam(value="cols") Long pBoardId){
		try {
			return new ResponseEntity<Board> (service.resumeBoard(1L, pBoardId),HttpStatus.OK);
		}catch (Exception e) {
			//other unexpected error
			return new ResponseEntity<Board> (HttpStatus.BAD_REQUEST);
		}
	}
}
