package com.deviget.minesweeper.controller;

import java.util.EnumSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deviget.minesweeper.dto.BoardDTO;
import com.deviget.minesweeper.dto.ResponseDTO;
import com.deviget.minesweeper.entity.Cell.CellFlag;
import com.deviget.minesweeper.service.BoardService;
import com.deviget.minesweeper.service.exception.BoardStatusException;
import com.deviget.minesweeper.service.exception.IndexOutOfBoardException;
import com.deviget.minesweeper.service.exception.RecordNotFoundException;

/**
 * Game controller
 * User interactions methods with the board
 * are provided in this class
 * @author amassillo
 *
 */
@RestController
@RequestMapping("/play")
public class GameController {
	

	@Autowired
	private BoardService service;

	@PostMapping (value = "/{boardId}/flag")
	public ResponseEntity<ResponseDTO> flagCell(@PathVariable(value="boardId") Long pBoardId,
										  @RequestParam(value="col", required=true) Integer pCol, 
										  @RequestParam(value="row", required=true) Integer pRow,
										  @RequestParam(value="flag", required=true) String pFlag,
										  @RequestParam(value="user_id", required=false) Long pUserId) throws BoardStatusException, IndexOutOfBoardException, RecordNotFoundException{
		ResponseDTO lDTO = null;
		EnumSet<CellFlag> lOptions = EnumSet.of(CellFlag.FLAG, CellFlag.QUESTION_MARK);
		CellFlag lFlag = CellFlag.valueOf(pFlag);
		if (lOptions.contains(lFlag)) {
				service.flagCell(pBoardId, pCol, pRow, lFlag);
				return new ResponseEntity<ResponseDTO> (new ResponseDTO("Cell has been flagged"),HttpStatus.OK);
		}else {
			lDTO = new ResponseDTO("Unknown flag type specified for the cell");
			return new ResponseEntity<ResponseDTO> (lDTO,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping (value = "/{boardId}/unflag")
	public ResponseEntity<ResponseDTO> unflagCell(@PathVariable(value="boardId") Long pBoardId,
										  @RequestParam(value="col", required=true) Integer pCol, 
										  @RequestParam(value="row", required=true) Integer pRow,
										  @RequestParam(value="user_id") Long pUserId) throws BoardStatusException, IndexOutOfBoardException, RecordNotFoundException{
		ResponseDTO lDTO = null;
		service.unflagCell(pBoardId, pCol, pRow);
		return new ResponseEntity<ResponseDTO> (new ResponseDTO("Cell has been unflagged"),HttpStatus.OK);
	}
	
	@PostMapping (value = "/{boardId}/click")
	public ResponseEntity<ResponseDTO> clickCell(@PathVariable(value="boardId", required=true) Long pBoardId,
										   @RequestParam(value="col", required=true) Integer pCol, 
										   @RequestParam(value="row", required=true) Integer pRow,
										   @RequestParam(value="user_id") Long pUserId) throws BoardStatusException, IndexOutOfBoardException, RecordNotFoundException{
		ResponseDTO lDTO = new BoardDTO();
		boolean lResult = service.clickCell(pBoardId, pCol, pRow);
		if(!lResult)
			lDTO.setMessages("Game's Over");
		else 
			lDTO.setMessages("Keep going, here's your board");
		//TODO
		return new ResponseEntity<ResponseDTO> (lDTO,HttpStatus.OK);
	}
}
