package com.deviget.minesweeper.controller.dtomapper;


import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import com.deviget.minesweeper.dto.CellDTO;
import com.deviget.minesweeper.entity.Cell;

/**
 * 
 * @author amassillo
 *
 */
@Mapper(componentModel = "spring")
public interface CellDTOMapper {

	 @BeforeMapping
	 default void beforeMapping(@MappingTarget CellDTO target, Cell source) {
		 	if (source == null || target == null)
		 		target.setValue("-");
		 	else {
		 		//to print bombs
			 	if(source.isHasBomb()) {
			 		target.setValue("*");
			 	}else
			 		target.setValue("-");
			 	if (source.getFlag() !=null)
			 	switch (source.getFlag()) {
			 		case FLAG:{
			 			target.setValue("F");
			 			break;
			 		} 
			 		case QUESTION_MARK:{
			 			target.setValue("?");
			 			break;
			 		}
			 		case NUMBER:{
			 			if (source.isClicked()) {
			 				target.setValue(source.getFlagValue().toString());
			 				break;
			 			}
			 		}
			 		default:{
			 		}
			 	}
		 	}
	 }
	@Mapping(target = "value", ignore = true) // This is now mapped in beforeMapping
	public CellDTO cellToCellDTOMapper(Cell pCell);
	public CellDTO[] cellToCellDTOMapper(Cell[] pCell);
}
