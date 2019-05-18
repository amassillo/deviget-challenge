package com.deviget.minesweeper.repository;

import org.springframework.stereotype.Repository;

import com.deviget.minesweeper.entity.Board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 * 
 * @author amassillo
 *
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{

	public List<Board> findBoardByUserId(Long pUserId);
	
	@Query(value="select id from Board u where u.user_id =:pUserId", nativeQuery=true)
	public List<Long> getBoardIdsbyUserId(Long pUserId);
	
}
