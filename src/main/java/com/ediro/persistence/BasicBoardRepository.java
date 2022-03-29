package com.ediro.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ediro.domain.board.BasicBoard;
import com.ediro.domain.board.QBasicBoard;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;

public interface BasicBoardRepository extends JpaRepository<BasicBoard,Long>,QuerydslPredicateExecutor<BasicBoard> {

	public default Predicate makePredicate(String type,String keyword){
			BooleanBuilder builder = new BooleanBuilder();
			
			QBasicBoard board = QBasicBoard.basicBoard;
			
			builder.and(board.bno.gt(0));
			
			if(type ==null)
				return builder;
			
			switch(type) {
			case "t":
				builder.and(board.title.like("%" + keyword+"%"));
				break;
			case "c":
				builder.and(board.content.like("%"+keyword+"%"));
				break;
			case "w":
				builder.and(board.writer.like("%"+keyword+"%"));
				break;
			}
			
			return builder;
	}
}
