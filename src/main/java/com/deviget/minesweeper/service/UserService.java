package com.deviget.minesweeper.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deviget.minesweeper.entity.User;
import com.deviget.minesweeper.repository.UserRepository;

/**
 * 
 * @author amassillo
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository repository;
		
	public User getUserById(Long pUserId){
		return repository.getOne(pUserId);
	}
	
}
