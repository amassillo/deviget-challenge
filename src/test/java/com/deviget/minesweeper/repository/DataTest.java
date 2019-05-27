package com.deviget.minesweeper.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.deviget.minesweeper.entity.Board;
import com.deviget.minesweeper.entity.User;
import com.deviget.minesweeper.entity.Board.Status;
import com.deviget.minesweeper.repository.BoardRepository;
import com.deviget.minesweeper.service.algo.BoardGenerator;
import com.deviget.minesweeper.service.algo.BoardGeneratorImpl;

import static org.assertj.core.api.Assertions.*;
/**
 * 
 * @author amassillo
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class DataTest {
	
	 @Autowired
	 private TestEntityManager entityManager;
	 
	 @Autowired
	 private BoardRepository boardRepository;
	 
	 @Autowired
	 private BoardGenerator boarGenerator;
	 
	 private Board board;
	 private User user;
	 @Before
	 public void setUp() {
		 user = new User();
		 user.setUname("amassillo");
		 entityManager.persist(user);
		 board =  boarGenerator.generateBoard(8, 8, 10); 
		 LocalDateTime lNow = LocalDateTime.now();
		 board.setStartDateTime(lNow);
		 board.setLastDateTimeStarted(lNow);
		 board.setStatus(Status.NEW);
		 board.setUserId(user.getId());
		 //this.boardRepository.save(board);
		 entityManager.persist(board);
		 entityManager.flush();
	 }
	 
	 @Test
	 public void testGetBoard() {
		 List<Long> lIds = boardRepository.getBoardIdsbyUserId(user.getId());
		 assertThat(lIds).isNotEmpty();
	 }
	 
	 @Test
	 public void testBoard() {
		 Board lBoard = boardRepository.getOne(board.getId());
		 assertThat(board).isEqualToComparingFieldByFieldRecursively(lBoard);
	 }
	 
	 
	 
}
