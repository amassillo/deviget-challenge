package com.deviget.minesweeper.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.deviget.minesweeper.entity.Board;
import com.deviget.minesweeper.entity.Cell;
import com.deviget.minesweeper.service.algo.BoardGenerator;
import com.deviget.minesweeper.service.algo.BoardGeneratorImpl;

import static org.assertj.core.api.Assertions.*;
/**
 * 
 * @author amassillo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=BoardGeneratorImpl.class)
public class BoardGenerationTest {

	@Autowired
	private BoardGenerator boarGenerator;
	
	
	@Test
	public void oneTest() {
		Board lBoard = boarGenerator.generateBoard(3, 4, 2);
		Cell[][] lCells = lBoard.getCells();
		
		int lPlacedMInes = 0;
		for (int i = 0; i < lBoard.getRows(); i++)
			for (int j = 0; j < lBoard.getCols(); j++) {
				Cell lCell = lCells[i][j];
				lPlacedMInes += (lCell !=null && lCell.isHasBomb()) ? 1:0;
			}
		assertThat(lPlacedMInes).isEqualTo(lBoard.getMines().intValue());
	}
}
