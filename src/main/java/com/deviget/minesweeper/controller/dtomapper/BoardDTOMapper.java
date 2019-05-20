package com.deviget.minesweeper.controller.dtomapper;


import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.deviget.minesweeper.dto.BoardDTO;
import com.deviget.minesweeper.dto.CellDTO;
import com.deviget.minesweeper.entity.Board;
import com.deviget.minesweeper.entity.Cell;
import com.deviget.minesweeper.entity.Board.Status;

/**
 * 
 * @author amassillo
 *
 */
@Mapper(componentModel = "spring", uses=CellDTOMapper.class)
public interface BoardDTOMapper {

	@AfterMapping
	default void aftetMapping(@MappingTarget BoardDTO target, Board source) {
		if (source!=null && !source.getStatus().equals(Status.FINALIZED)) {
			for (int i=0;i< source.getRows(); i++)
				for (int j=0;j< source.getCols(); j++) {
					Cell lCell = source.getCells()[i][j];
					if (lCell == null) {
						//standarize
						target.getCells()[i][j] = new CellDTO("-");
					}else if(lCell.isHasBomb()) {
						//do not reveal
						target.getCells()[i][j].setValue("-");
					}
				}
					
		}
	}
	public BoardDTO boardToBoardDTOMapper(Board pBoard);
}
