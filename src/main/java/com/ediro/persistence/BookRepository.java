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

	public default Predicate makePredicate(Book bookVO) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); 
		
		BooleanBuilder builder = new BooleanBuilder();
		QBook book = QBook.book;
		builder.and(book.member.memberID.eq(name));
		
		if(bookVO.getBookTitle() != null && !bookVO.getBookTitle().isEmpty())
			builder.and(book.bookTitle.contains(bookVO.getBookTitle()));
		
		if(bookVO.getBarcode() != null && !bookVO.getBarcode().isEmpty())
			builder.and(book.barcode.contains(bookVO.getBarcode()));
		
			return builder;
	}
	
	public List<Book> findByBookTitleContaining(String title);
	public List<Book> findByMember_MemberID(String memberID);
}