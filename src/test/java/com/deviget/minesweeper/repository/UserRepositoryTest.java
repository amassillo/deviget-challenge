package com.deviget.minesweeper.repository;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.deviget.minesweeper.entity.User;
import com.deviget.minesweeper.repository.UserRepository;

import static org.assertj.core.api.Assertions.*;
/**
 * 
 * @author amassillo
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	 @Autowired
	 private TestEntityManager entityManager;
	 
	 @Autowired
	 private UserRepository repository;
	 
	 private User user;
	 
	 @Before
	 public void setUp() {
		 user = new User();
		 user.setUname("testUser");
		 
		 this.entityManager.persistAndFlush(user);
	 }
	 
	 @Test
	 public void testGetUser() {
		 User lUser = repository.getOne(user.getId());
		 assertThat(lUser.getId()).isEqualTo(user.getId());
	 }	 
	 
	 
}
