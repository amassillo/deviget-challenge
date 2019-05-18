package com.deviget.minesweeper.controller.dtomapper;


import org.mapstruct.Mapper;

import com.deviget.minesweeper.dto.BoardDTO;
import com.deviget.minesweeper.entity.Board;

/**
 * 
 * @author amassillo
 *
 */
@Mapper(componentModel = "spring", uses=CellDTOMapper.class)
public interface BoardDTOMapper {

	public BoardDTO boardToBoardDTOMapper(Board pBoard);
}
