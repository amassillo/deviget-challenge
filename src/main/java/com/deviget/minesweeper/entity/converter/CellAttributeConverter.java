package com.deviget.minesweeper.entity.converter;

import java.io.IOException;

import javax.persistence.AttributeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deviget.minesweeper.entity.Cell;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author amassillo
 *
 */
public class CellAttributeConverter implements AttributeConverter<Cell[][], String> {

	private ObjectMapper objectMapper = new ObjectMapper();
	private static final Logger logger = LoggerFactory.getLogger(CellAttributeConverter.class);
	
	public String convertToDatabaseColumn(Cell[][] arg0) {
		 String cellInfoJson = null;
	        try {
	        	cellInfoJson = objectMapper.writeValueAsString(arg0);
	        } catch (final JsonProcessingException e) {
	            logger.error("JSON writing error", e);
	        }
	 
	        return cellInfoJson;
	}

	public Cell[][] convertToEntityAttribute(String arg0) {
		 Cell[][] lCellInfo = null;
		  try {
			  lCellInfo = objectMapper.readValue(arg0,  Cell[][].class);
	        } catch (final IOException e) {
	            logger.error("JSON reading error", e);
	        }
		return lCellInfo;
	}
		
}
