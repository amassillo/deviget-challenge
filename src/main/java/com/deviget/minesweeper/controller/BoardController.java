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

import com.deviget.minesweeper.controller.dtomapper.BoardDTOMapper;
import com.deviget.minesweeper.dto.BoardDTO;
import com.deviget.minesweeper.dto.ResponseIdsDTO;
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
	
	@Autowired
	private BoardDTOMapper mapper;

	@GetMapping (value = "/")
	//TODO move to DTO BoardDTO
	public ResponseEntity<ResponseDTO> getBoards(@RequestParam(value="user_id") Long pUserId){
		try {
			List<Long> lIds = service.getUserBoards(pUserId);
			ResponseIdsDTO lDTO = new ResponseIdsDTO(lIds);
			return new ResponseEntity<ResponseDTO> (lDTO,HttpStatus.OK);//TODO
		}catch (Exception e) {
			//other unexpected error
			return new ResponseEntity<ResponseDTO> (HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * prints board
	 * @param pBoardId
	 * @return
	 */
	@GetMapping (value = "/{boardId}")
	public ResponseEntity<ResponseDTO> getBoard(@PathVariable(value="boardId") Long pBoardId){
		try {
			Board lBoard = service.getBoard(pBoardId);
			if (lBoard !=null) {
				return new ResponseEntity<ResponseDTO> (mapper.boardToBoardDTOMapper(lBoard),HttpStatus.OK);
			}else
				return new ResponseEntity<ResponseDTO> (new ResponseDTO("Board not found"),HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			//other unexpected error
			logger.error(e.getMessage());
			return new ResponseEntity<ResponseDTO> (new ResponseDTO("An unexpected error has occurred"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping (value = "/")
	public ResponseEntity<ResponseDTO> newBoard(@RequestParam(value="userId", required = true) Long pUserId,
										 @RequestParam(value="cols", required = true) Integer pCols, 
										 @RequestParam(value="rows", required = true) Integer pRows,
										 @RequestParam(value="mines", required = true) Integer pNbrOfMines){
		//some parameters validation
		if (pNbrOfMines > pRows * pCols) {
			ResponseDTO lDTO = new ResponseDTO("Number of mines should be lower than board's total cells");
			return new ResponseEntity<ResponseDTO> (lDTO,HttpStatus.BAD_REQUEST);//TODO
		}
		Board lBoard = service.startNewGame(pUserId, pCols, pRows, pNbrOfMines);
		ResponseIdsDTO lDTO = new ResponseIdsDTO(lBoard.getId());
		return new ResponseEntity<ResponseDTO> (lDTO,HttpStatus.OK);
	}
	
	@PostMapping (value = "/{boardId}/resume")
	public ResponseEntity<ResponseIdsDTO> resumeBoard(@PathVariable(value="boardId") Long pBoardId,
											 	   @RequestParam(value="userId") Long pUserId){
		try {
			service.resumeGame(pUserId, pBoardId);
			return new ResponseEntity<ResponseIdsDTO> (HttpStatus.OK);//TODO
		}catch (Exception e) {
			//other unexpected error
			return new ResponseEntity<ResponseIdsDTO> (HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping (value = "/{boardId}/pause")
	public ResponseEntity<ResponseIdsDTO> pauseBoard(@PathVariable(value="boardId") Long pBoardId,
											 	   @RequestParam(value="userId") Long pUserId){
		try {
			service.pauseGame(pUserId, pBoardId);
			return new ResponseEntity<ResponseIdsDTO> (HttpStatus.OK);//TODO
		}catch (Exception e) {
			//other unexpected error
			return new ResponseEntity<ResponseIdsDTO> (HttpStatus.BAD_REQUEST);
		}
	}

}
