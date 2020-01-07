package com.ediro.persistence;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ediro.domain.Book;
import com.ediro.domain.Member;
import com.ediro.domain.QBasket;
import com.ediro.domain.QBook;
import com.ediro.security.EdiroSecurityUser;
import com.ediro.vo.BookBascketVO;
import com.ediro.vo.BookVO;
import com.ediro.vo.CusBasketVO;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

@Repository
public class CustomBookRepositoryImpl extends QuerydslRepositorySupport implements CustomBookRepository {

	@Autowired
	MemberRepository memberRepository;
	
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

	@Override
	public Page<BookBascketVO> getBookListWithCartInfo(BookVO bookvo,Pageable page,Principal principal){
		
		Member user = memberRepository.findMemberByMemberID(principal.getName());
		
			QBook qbook = QBook.book;
			QBasket qbasket = QBasket.basket;
				
			JPQLQuery<BookBascketVO> query = from(qbook)
		    		.leftJoin(qbasket).on(qbook.bookCode.eq(qbasket.book.bookCode)).on(qbasket.member.eq(user))
		    		.select(Projections.bean(BookBascketVO.class,
		    								 qbook.bookCode,
	    									 qbook.bookTitle,
	    									 qbook.author,
	    									 qbook.publisher,
	    									 qbook.pubDate,
	    									 qbook.price,
	    									 qbook.barcode,
	    									 qbasket.orderQty));
		  
		   if(StringUtils.hasText(bookvo.getBookTitle()))
		   {
			   query.where(qbook.bookTitle.contains(bookvo.getBookTitle()));
		   }
		   
		   if(StringUtils.hasText(bookvo.getBarcode()))
		   {
			   query.where(qbook.barcode.contains(bookvo.getBarcode()));
		   }
		   
		   if(StringUtils.hasText(bookvo.getAuthor()))
		   {
			   query.where(qbook.author.contains(bookvo.getAuthor()));
		   }
		  
		   if(StringUtils.hasText(bookvo.getPublisher()))
		   {
			   query.where(qbook.publisher.contains(bookvo.getPublisher()));
		   }
			
		   
		   final List<BookBascketVO> lstbook = getQuerydsl().applyPagination(page, query).fetch();
	        return new PageImpl<BookBascketVO>(lstbook, page, query.fetchCount());
	}
}
