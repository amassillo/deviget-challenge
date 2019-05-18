package com.deviget.minesweeper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deviget.minesweeper.dto.ResponseDTO;
import com.deviget.minesweeper.dto.ResponseIdsDTO;
import com.deviget.minesweeper.service.UserService;

/**
 * 
 * @author amassillo
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService service;
	
	@PostMapping (value = "/")
	public ResponseEntity<ResponseDTO> createUser( @RequestParam(value="uname", required=true) String pUname){
		Long lUserId = service.saveUser(pUname);
		ResponseIdsDTO lDTO = new ResponseIdsDTO(lUserId);
		return new ResponseEntity<ResponseDTO> (lDTO,HttpStatus.OK);//TODO
	}
}
