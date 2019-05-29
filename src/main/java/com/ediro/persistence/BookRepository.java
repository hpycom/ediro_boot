package com.ediro.persistence;


import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.web.config.QuerydslWebConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ediro.domain.Book;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import com.ediro.domain.QBook;


/**
 * @author hpycom
 * @Date 2017-12-24
 * @GitHub : https://github.com/
 */
public interface BookRepository extends CrudRepository<Book, String>,
QuerydslPredicateExecutor<Book>{

	public default Predicate makePredicate(String Type,String keyword) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); 
		
		BooleanBuilder builder = new BooleanBuilder();
		QBook book = QBook.book;
		builder.and(book.member.memberID.eq(name));
		return builder;
	}
	
	public List<Book> findByBookTitleContaining(String title);
	public List<Book> findByMember_MemberID(String memberID);
}