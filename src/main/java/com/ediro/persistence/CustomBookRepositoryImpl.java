package com.ediro.persistence;

import java.util.List;


import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ediro.domain.Book;
import com.ediro.domain.QBook;
import com.ediro.vo.BookVO;
import com.querydsl.jpa.JPQLQuery;

@Repository
public class CustomBookRepositoryImpl extends QuerydslRepositorySupport implements CustomBookRepository {

	public CustomBookRepositoryImpl() {
		super(Book.class);
	}
	
	@Override
	public List<Book> search(BookVO vbook) {
	   
	   QBook book = QBook.book;
	   
	   JPQLQuery<Book> query = from(book);
	  
	   if(StringUtils.hasText(vbook.getBookTitle()))
	   {
		   query.where(book.bookTitle.contains(vbook.getBookTitle()));
	   }
		
	   List<Book> result = query.fetch();
	   
	   return result;
	}

}
