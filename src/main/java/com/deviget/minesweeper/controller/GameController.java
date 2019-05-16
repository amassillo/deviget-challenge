package com.deviget.minesweeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deviget.minesweeper.dto.ResponseDTO;
import com.deviget.minesweeper.service.BoardService;

/**
 * 
 * @author amassillo
 *
 */
@RestController
@RequestMapping("/play")
public class GameController {
	
	
	@Autowired
	private BoardService service;

	@PutMapping (value = "/{boardId}/flag")
	public ResponseEntity<ResponseDTO> flagCell(@PathVariable(value="boardId") Integer pBoardId,
										  @RequestParam(value="col") Integer pCol, 
										  @RequestParam(value="row") Integer pRow,
										  @RequestParam(value="flag") Integer pFlag){
		ResponseDTO lDTO = null;
		//TODO
		return new ResponseEntity<ResponseDTO> (lDTO,HttpStatus.OK);
	}
	
	@PostMapping (value = "/{boardId}/click")
	public ResponseEntity<ResponseDTO> clickCell(@PathVariable(value="boardId", required=true) Long pBoardId,
										   @RequestParam(value="col", required=true) Integer pCol, 
										   @RequestParam(value="row", required=true) Integer pRow){
		ResponseDTO lDTO = null;
		service.clickCell(pBoardId, pCol, pRow);;
		return new ResponseEntity<ResponseDTO> (lDTO,HttpStatus.OK);
	}
}
