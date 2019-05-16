package com.deviget.minesweeper.repository;

import org.springframework.stereotype.Repository;
import com.deviget.minesweeper.entity.User;


import org.springframework.data.jpa.repository.JpaRepository;
/**
 * 
 * @author amassillo
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
