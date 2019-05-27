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
		 	if (source == null)
		 		target.setValue("-");
		 	else {
		 		//to print bombs
			 	if(source.isHasBomb()) {
			 		target.setValue("*");
			 	}else if (source.isClicked()) {
				 		if(source.getFlagValue() >0)
				 			target.setValue(source.getFlagValue().toString());
				 		else
				 			target.setValue("");
	 					}else {
	 						target.setValue("-");
	 					}
			 	//overwrite if cell has a flag
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
			 		default:{
			 			
			 		}
			 	}
		 	}
	 }
	@Mapping(target = "value", ignore = true) // This is now mapped in beforeMapping
	public CellDTO cellToCellDTOMapper(Cell pCell);
	public CellDTO[] cellToCellDTOMapper(Cell[] pCell);
}
