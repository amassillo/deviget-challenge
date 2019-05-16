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
	
	@Autowired
	private BoardGenerator boardGenerator;
	/**
	 * 
	 * @param pUserId
	 * @return
	 */
	public List<Board> getUserBoards(Long pUserId){
		return repository.findBoardByUserId(pUserId);
	}
	
	public Board getBoard(Long pBoardId){
		return repository.getOne(pBoardId);
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
		Board lBoard = boardGenerator.generateBoard(pCols, pRows, pNbrOfMines);
		//tie to user
		User lUser = userService.getUserById(pUserId);
		
		if (lUser!=null)
			lBoard.setUserId(pUserId);
		else
			throw new EntityNotFoundException("User not found"); //TODO
		//save
		return this.saveUserBoard(lBoard);
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
	public Board saveUserBoard(Board pBoard) {
		return repository.save(pBoard);
	}
	
}
