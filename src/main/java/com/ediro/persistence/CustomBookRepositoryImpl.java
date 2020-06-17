package com.ediro.persistence;

import java.security.Principal;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ediro.controller.BookOrderController;
import com.ediro.domain.Book;
import com.ediro.domain.BookStatus;
import com.ediro.domain.Member;
import com.ediro.domain.QBasket;
import com.ediro.domain.QBook;
import com.ediro.domain.QMemberBookDiscount;
import com.ediro.vo.BookBascketVO;
import com.ediro.vo.BookVO;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.java.Log;

@Log
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
	   
	   if(StringUtils.hasText(vbook.getBarcode()))
	   {
		   query.where(book.barcode.contains(vbook.getBarcode()));
	   }

	   if(StringUtils.hasText(vbook.getStartPubdate()) && StringUtils.hasText(vbook.getEndPubdate()))
	   {
		   query.where(book.pubDate.goe(vbook.getStartPubdate()));
		   query.where(book.pubDate.loe(vbook.getEndPubdate()));
		   
	   }
	   
	   
	   if(StringUtils.hasText(vbook.getBookstatus()))
	   {
			 BookStatus bs =   BookStatus.valueOf(vbook.getBookstatus());
			 log.info(bs.getCode());
		   query.where(book.bookstatus.eq( com.ediro.domain.BookStatus.valueOf(vbook.getBookstatus())));
	   }
	   query.where(book.delYN.eq("N"));
	

	   
	   List<Book> result = query.fetch();
	   
	   return result;
	}

	@Override
	public Page<BookBascketVO> getBookListWithCartInfo(BookVO bookvo,Pageable page,Principal principal){
		
		Member user = memberRepository.findMemberByMemberID(principal.getName());
		
			QBook qbook = QBook.book;
			QBasket qbasket = QBasket.basket;
			QMemberBookDiscount qMembookDiscnt = QMemberBookDiscount.memberBookDiscount;
			
			JPQLQuery<BookBascketVO> query = from(qbook)
		    		.leftJoin(qbasket).on(qbook.bookCode.eq(qbasket.book.bookCode)).on(qbasket.member.eq(user))
		    		.leftJoin(qMembookDiscnt).on(qMembookDiscnt.book.eq(qbook)).on(qMembookDiscnt.member.eq(user))
		    		.select(Projections.bean(BookBascketVO.class,
		    								 qbook.bookCode,
	    									 qbook.bookTitle,
	    									 qbook.author,
	    									 qbook.publisher,
	    									 qbook.pubDate,
	    									 qbook.price,
	    									 qbook.barcode,
	    									 qbasket.orderQty,
	    									 qbook.dcPercent,
	    									 qMembookDiscnt.discountPct,
	    									 qbook.bookstatus));
		  
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
			
		   query.where(qbook.delYN.eq("N"));
		 
		   final List<BookBascketVO> lstbook = getQuerydsl().applyPagination(page, query).fetch();
		    return new PageImpl<BookBascketVO>(lstbook, page, query.fetchCount());
	}
}
