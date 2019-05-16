package com.deviget.minesweeper.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deviget.minesweeper.dto.BoardDTO;
import com.deviget.minesweeper.dto.ResponseDTO;
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

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	
	@Autowired
	private BoardService service;

	@GetMapping (value = "/")
	//TODO move to DTO BoardDTO
	public ResponseEntity<List<Board>> getBoards(@RequestParam(value="user_id") Long pUserId){
		try {
			return new ResponseEntity<List<Board>> (service.getUserBoards(pUserId),HttpStatus.OK);
		}catch (Exception e) {
			//other unexpected error
			return new ResponseEntity<List<Board>> (HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping (value = "/{boardId}")
	public ResponseEntity<Board> getBoard(@PathVariable(value="boardId") Long pBoardId){
		try {
			return new ResponseEntity<Board> (service.getBoard(pBoardId),HttpStatus.OK);
		}catch (Exception e) {
			//other unexpected error
			return new ResponseEntity<Board> (HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping (value = "/")
	public ResponseEntity<ResponseDTO> newBoard(@RequestParam(value="userId", required = true) Long pUserId,
										 @RequestParam(value="cols", required = true) Integer pCols, 
										 @RequestParam(value="rows", required = true) Integer pRows,
										 @RequestParam(value="mines", required = true) Integer pNbrOfMines){
		ResponseDTO lDTO = null;
		//some parameters validation
		if (pNbrOfMines > pRows * pCols) {
			lDTO = new ResponseDTO("Number of mines should be lower than board's total cells");
			return new ResponseEntity<ResponseDTO> (lDTO, HttpStatus.OK);
		}
		Board lBoard = service.startNewGame(pUserId, pCols, pRows, pNbrOfMines);
		BoardDTO lBoardDTO = new BoardDTO();
		return new ResponseEntity<ResponseDTO> (lBoardDTO, HttpStatus.OK);
	}
	
	@PutMapping (value = "/{boardId}")
	public ResponseEntity<Board> resumeBoard(@PathVariable(value="boardId") Long pBoardId,
											 @RequestParam(value="userId") Long pUserId){
		try {
			return new ResponseEntity<Board> (service.resumeBoard(pUserId, pBoardId),HttpStatus.OK);
		}catch (Exception e) {
			//other unexpected error
			return new ResponseEntity<Board> (HttpStatus.BAD_REQUEST);
		}
	}
}
