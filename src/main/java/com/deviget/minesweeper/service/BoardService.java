package com.deviget.minesweeper.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.deviget.minesweeper.entity.Board;
import com.deviget.minesweeper.entity.Cell;
import com.deviget.minesweeper.entity.Board.Status;
import com.deviget.minesweeper.entity.User;
import com.deviget.minesweeper.repository.BoardRepository;
import com.deviget.minesweeper.service.algo.BoardActions;
import com.deviget.minesweeper.service.algo.BoardGenerator;
import com.deviget.minesweeper.service.exception.BoardStatusException;
import com.deviget.minesweeper.service.exception.IndexOutOfBoardException;
import com.deviget.minesweeper.service.exception.NotYourBoardException;
import com.deviget.minesweeper.service.exception.RecordNotFoundException;

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
	
	@Autowired
	private BoardActions commandRecorder;
	/**
	 * 
	 * @param pUserId
	 * @return
	 */
	public List<Long> getUserBoards(Long pUserId){
		return repository.getBoardIdsbyUserId(pUserId);
	}
	
	
	/**
	 * 
	 * @param pUserId
	 * @param pCols
	 * @param pRows
	 * @param pNbrOfMines
	 * @throws RecordNotFoundException 
	 */
	public Board startNewGame(Long pUserId, Integer pCols, 
							 Integer pRows, Integer pNbrOfMines) throws RecordNotFoundException {
		Board lBoard = boardGenerator.generateBoard(pCols, pRows, pNbrOfMines);
		//tie to user
		User lUser = userService.getUserById(pUserId);
		
		if (lUser!=null)
			lBoard.setUserId(pUserId);
		else
			throw new RecordNotFoundException(pUserId); //TODO
		lBoard.setStatus(Status.NEW);
		//save
		return this.saveUserBoard(lBoard);
	}
	
	/**
	 * 
	 * @param pBoardId
	 * @param pCol
	 * @param pRow	
	 * @throws BoardStatusException 
	 * @throws IndexOutOfBoardException 
	 * @throws RecordNotFoundException 
	 * @throws NotYourBoardException 
	 */
	public boolean clickCell(Long pBoardId, Integer pCol, Integer pRow, Long pUserId) throws BoardStatusException, IndexOutOfBoardException, RecordNotFoundException, NotYourBoardException {
		Board lBoard = getBoard(pBoardId);
		//might throw exception -----------------------------
		checkBoardExists(pBoardId, lBoard);
		runBoardChecks(lBoard, pRow,pCol);
		checkBoarOwnership(pUserId,lBoard);
		//end of might throw exception ----------------------
		boolean lResult = this.commandRecorder.click (lBoard, pCol, pRow);
		//if board is new, then set status = ONGOING
		//update board status if game has ended (according command result)
		if (lResult) {
			if (!hasAnyRemainingActionToPerform(lBoard)) {
				lBoard.setStatus(Status.FINALIZED);
				lBoard.setResult(true);
				this.calculateBoardPlayTime(lBoard);
			}else if (lBoard.getStatus().equals(Status.NEW))
						lBoard.setStatus(Status.ON_GOING);
		}else {
			lBoard.setStatus(Status.FINALIZED);
			lBoard.setResult(false);
			this.calculateBoardPlayTime(lBoard);
		}
		//save partial result
		this.saveUserBoard(lBoard);
		return lResult;
	}
	
	/**
	 * 
	 * @param pBoardId
	 * @param pCol
	 * @param pRow	
	 * @throws BoardStatusException 
	 * @throws IndexOutOfBoardException 
	 * @throws RecordNotFoundException 
	 * @throws NotYourBoardException 
	 */
	public boolean flagCell(Long pBoardId, Integer pCol, Integer pRow, Cell.CellFlag pFlag, Long pUserId ) throws BoardStatusException, IndexOutOfBoardException, RecordNotFoundException, NotYourBoardException {
		Board lBoard = getBoard(pBoardId);
		//might throw exception -----------------------------
		checkBoardExists(pBoardId, lBoard);
		runBoardChecks(lBoard, pRow,pCol);
		checkBoarOwnership(pUserId,lBoard);
		//end of might throw exception ----------------------
		boolean lResult = this.commandRecorder.flag(lBoard,pFlag, pCol, pRow);
		//normalize cords 
		if (lResult) {
			this.saveUserBoard(lBoard);//save partial result
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param pBoardId
	 * @param pCol
	 * @param pRow
	 * @throws IndexOutOfBoardException 
	 * @throws BoardStatusException 
	 * @throws RecordNotFoundException 
	 * @throws NotYourBoardException 
	 */
	public boolean unflagCell(Long pBoardId, Integer pCol, Integer pRow, Long pUserId) throws BoardStatusException, IndexOutOfBoardException, RecordNotFoundException, NotYourBoardException {
		Board lBoard = getBoard(pBoardId);
		//might throw exception -----------------------------
		checkBoardExists(pBoardId, lBoard);
		runBoardChecks(lBoard, pRow,pCol);
		checkBoarOwnership(pUserId,lBoard);
		//end of might throw exception ----------------------
		Boolean lResult = this.commandRecorder.unflag(lBoard, pCol, pRow);
		//normalize cords
		if (lResult) {
			this.saveUserBoard(lBoard);//save partial result
			return true;
		}return false;
		//
	}
	
	/**
	 * 
	 * @param pUserId
	 * @param pBoardId
	 * @return
	 * @throws BoardStatusException 
	 * @throws RecordNotFoundException 
	 * @throws NotYourBoardException 
	 */
	public Board pauseGame(Long pUserId, Long pBoardId) throws BoardStatusException, RecordNotFoundException, NotYourBoardException {
		Board lBoard = getBoard(pBoardId);
		 //might throw exception ----------------------
		checkBoarOwnership(pUserId,lBoard);
		checkBoardStatus(lBoard);
		 //end of might throw exception----------------
		lBoard.setStatus(Status.PAUSED);
		this.calculateBoardPlayTime(lBoard);
		return this.saveUserBoard(lBoard);
	}
	
	/**
	 * 
	 * @param pUserId
	 * @param pBoardId
	 * @return
	 * @throws BoardStatusException 
	 * @throws RecordNotFoundException 
	 * @throws NotYourBoardException 
	 */
	public Board resumeGame(Long pUserId, Long pBoardId) throws BoardStatusException, RecordNotFoundException, NotYourBoardException {
		Board lBoard = getBoard(pBoardId);
		 //might throw exception ----------------------
		checkBoarOwnership(pUserId,lBoard);
		if (lBoard.getStatus().equals(Status.FINALIZED))
			throw new BoardStatusException(lBoard.getStatus(),"");
		 //end of might throw exception --------------
		lBoard.setLastDateTimeStarted(LocalDateTime.now());
		lBoard.setStatus(Status.ON_GOING);
		return this.saveUserBoard(lBoard);
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
	
	/**
	 * 
	 * @param pUserId
	 * @param pBoard
	 * @throws NotYourBoardException
	 */
	public void checkBoarOwnership(Long pUserId, Board pBoard) throws NotYourBoardException {
		if (pBoard.getUserId() !=null && pUserId != pBoard.getUserId())
			throw new NotYourBoardException();
	}
	
	/**
	 * 
	 * @param pProvidedId
	 * @param pBoard
	 * @throws RecordNotFoundException
	 */
	public void checkBoardExists(Long pProvidedId, Board pBoard) throws RecordNotFoundException {
		if (pBoard == null)
			throw new RecordNotFoundException(pProvidedId);
	}
	/**
	 * 
	 * @param pBoard
	 * @throws BoardStatusException
	 */
	private void checkBoardStatus(Board pBoard) throws BoardStatusException {
		if (pBoard.getStatus().equals(Status.FINALIZED))
			throw new BoardStatusException(pBoard.getStatus(),"");
		
	}
	/**
	 * 
	 * @param pBoard
	 * @throws BoardStatusException
	 */
	private void runBoardChecks(Board pBoard, int pRow, int pCol) throws BoardStatusException,IndexOutOfBoardException {
		checkBoardStatus(pBoard);
		if (pCol < 1 || pCol > pBoard.getCols() ||
				pRow < 1 || pRow > pBoard.getRows())
			throw new IndexOutOfBoardException();
	}
	
	/**
	 * This can be calculated every time a cell is revealed and stored in db
	 * for future work
	 * @param pBoard
	 * @return
	 */
	private boolean hasAnyRemainingActionToPerform(Board pBoard) {
		for (int i=0; i< pBoard.getRows(); i++)
			for (int j=0; j< pBoard.getCols(); j++) {
				Cell lCell = pBoard.getCells()[i][j];
				if (!lCell.isHasBomb() && !lCell.isClicked())
					return true;
			}
		return false;
	}
	
	/**
	 * 
	 * @param pBoard
	 */
	private void calculateBoardPlayTime(Board pBoard) {
		Duration timeDiff = Duration.between(pBoard.getLastDateTimeStarted() , LocalDateTime.now());
		timeDiff.plus(pBoard.getDuration());
		pBoard.setDuration(timeDiff);
	}
	
	/**
	 * 
	 * @param pBoardId
	 * @return
	 * @throws RecordNotFoundException
	 */
	public Board getBoard(Long pBoardId) throws RecordNotFoundException {
		Board lBoard = null;
		try {
			lBoard = this.repository.getOne(pBoardId);
		}catch(JpaObjectRetrievalFailureException ex) {
			throw new RecordNotFoundException(pBoardId);
		}
		return lBoard;
	}
}
