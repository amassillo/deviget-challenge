package com.deviget.minesweeper.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.deviget.minesweeper.entity.Board;
import com.deviget.minesweeper.repository.BoardRepository;
import com.deviget.minesweeper.repository.UserRepository;
import com.deviget.minesweeper.service.BoardService;
import com.deviget.minesweeper.service.exception.RecordNotFoundException;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
/**
 * 
 * @author amassillo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardServiceTest {

	@Autowired
	private BoardService service;
	
	@MockBean
	private BoardRepository repository;
	
	@MockBean
	private UserRepository userRepository;
	
	@Before
	public void setUp() {
		
	    Board b1 = new Board();
	    b1.setId(1L);
	    Board b2 = new Board();
	    b2.setId(2L);

	    List<Long> boardIds = Arrays.asList(1L,2L);
	    Mockito.when(repository.getOne(1L)).thenReturn(b1);
	    Mockito.when(repository.getOne(2L)).thenReturn(b2);
	    Mockito.when(repository.getBoardIdsbyUserId(1L)).thenReturn(boardIds);
	}
	
	@Test
	public void testGetBoardService() throws RecordNotFoundException {
		Board lBoard = service.getBoard(1L);
		assertThat(lBoard.getId()).isEqualTo(1L);
	}
}
