package com.deviget.minesweeper.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.deviget.minesweeper.service.algo.BoardGenerator;
import com.deviget.minesweeper.service.algo.BoardGeneratorImpl;



/**
 * 
 * @author amassillo
 *
 */
@ComponentScan(basePackages = "com.deviget.minesweeper")
@EnableJpaRepositories(basePackages = "com.deviget.minesweeper.repository")
@EntityScan(basePackages = "com.deviget.minesweeper.entity")
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
	
	@Bean
	public BoardGenerator getBoardGenerator() {
		return new BoardGeneratorImpl();
	}
	
/*
	@Autowired
	private CellDTOMapper cellDTOMapper;

	@Bean
	public CellDTOMapper getCellDTOMapper() {
		return cellDTOMapper;
	}*/
}
