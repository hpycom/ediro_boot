package com.ediro;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.ediro.domain.board.BasicBoard;
import com.ediro.persistence.BasicBoardRepository;
import lombok.extern.java.Log;


@RunWith(SpringRunner.class)
@SpringBootTest
@Commit
@Log
public class BasicBoardRepositoryTests {
	
	@Autowired
	BasicBoardRepository repo;
	
	//@Test
	public void insertBoardDummies() {
		IntStream.range(0,300).forEach(i->{
			BasicBoard board = new BasicBoard();
			
			board.setTitle("sample Board Title " + i);
			board.setContent("Content Sample ..." + i + "of Board");
			board.setWriter("user0" + (i%10));
			
			repo.save(board);
		});
	}
	
	//@Test
	public void testList1() {
		Pageable pageable = PageRequest.of(0, 20,Direction.DESC,"bno");
		Page<BasicBoard> result = repo.findAll(repo.makePredicate(null,null),pageable);
		
		result.getContent().forEach(board -> log.info(""+board));
	}
	
	@Test
	public void testList2() {
		Pageable pageable = PageRequest.of(0, 20,Direction.DESC,"bno");
		
		Page<BasicBoard> result = repo.findAll(repo.makePredicate("t","10"),pageable);
		
		result.getContent().forEach(board->log.info("" +board));
	}
}
