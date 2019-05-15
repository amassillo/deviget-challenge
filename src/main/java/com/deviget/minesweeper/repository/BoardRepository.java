package com.deviget.minesweeper.repository;

import org.springframework.stereotype.Repository;

import com.deviget.minesweeper.entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * 
 * @author amassillo
 *
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{

}
