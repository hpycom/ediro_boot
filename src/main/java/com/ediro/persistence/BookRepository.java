package com.ediro.persistence;


import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.web.config.QuerydslWebConfiguration;

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
		BooleanBuilder builder = new BooleanBuilder();
		QBook book = QBook.book;
		builder.and(book.bookCode.gt(0));
		return builder;
	}
	
	public List<Book> findByBookTitleContaining(String title);
	public List<Book> findByMember_MemberID(String memberID);
}