package com.deviget.minesweeper.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deviget.minesweeper.entity.Board;
import com.deviget.minesweeper.entity.User;
import com.deviget.minesweeper.repository.BoardRepository;
import com.deviget.minesweeper.service.algo.BoardGenerator;

/**
 * 
 * @author amassillo
 *
 */
@Service
public class BoardService {

	@Autowired
	private BoardRepository repository;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * @param pUserId
	 * @return
	 */
	public List<Board> getUserBoards(Long pUserId){
		return repository.findBoardByUserId(pUserId);
	}
	
	/**
	 * 
	 * @param pUserId
	 * @param pCols
	 * @param pRows
	 * @param pNbrOfMines
	 */
	public Board startNewGame(Long pUserId, Integer pCols, 
							 Integer pRows, Integer pNbrOfMines) {
		Board lBoard = new Board();
		lBoard.setRows(pRows);
		lBoard.setCols(pCols);
		lBoard.setMines(pNbrOfMines);
		//tie to user
		User lUser = userService.getUserById(pUserId);
		if (lUser!=null)
			lBoard.setUser(lUser);
		else
			throw new EntityNotFoundException(""); //TODO
		
		//generate mines location
		Cell[][] lCells = BoardGenerator.generateBoard(lBoard, pCols, 
														pRows, pNbrOfMines);
		//set mined cells
		lBoard.setCells(lCells);
		//save
		return this.saveUserBoard(pUserId, lBoard);
	}
	
	/**
	 * 
	 * @param pBoardId
	 * @param pCol
	 * @param pRow
	 */
	public void clickCell(Long pBoardId, Integer pCol, Integer pRow) {
		Board lBoard = repository.getOne(pBoardId);
		
	}
	
	/**
	 * 
	 * @param pUserId
	 * @param pBoardId
	 * @return
	 */
	public Board resumeBoard(Long pUserId, Long pBoardId) {
		return null;
	}
	
	/**
	 * 
	 * @param pUserId
	 * @param pBoard
	 * @return
	 */
	public Board saveUserBoard(Long pUserId, Board pBoard) {
		return repository.save(pBoard);
	}
	
}
