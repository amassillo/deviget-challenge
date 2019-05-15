package com.deviget.minesweeper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deviget.minesweeper.entity.Board;
import com.deviget.minesweeper.repository.BoardRepository;

/**
 * 
 * @author amassillo
 *
 */
@Service
public class BoardService {

	@Autowired
	private BoardRepository repository;
	
	public List<Board> getUserBoards(Long pUserId){
		return null;
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
		//generate mines location
		lBoard.setMinedCells("");
		return this.saveUserBoard(pUserId, lBoard);
	}
	
	public Board resumeBoard(Long pUserId, Long pBoardId) {
		return null;
	}
	public Board saveUserBoard(Long pUserId, Board pBoard) {
		
		
		return repository.save(pBoard);
	}
	
}
