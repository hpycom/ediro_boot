package com.ediro.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ediro.domain.Basket;

import com.ediro.domain.QBasket;
import com.ediro.domain.QBook;
import com.ediro.security.EdiroSecurityUser;
import com.ediro.vo.CusBasketVO;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
@Repository
public class CustomBascketRepositoryImpl  extends QuerydslRepositorySupport implements CustomBascketRepository {

	public CustomBascketRepositoryImpl() {
		super(Basket.class);
	}
	
	@Override
	public List<CusBasketVO> search(@AuthenticationPrincipal EdiroSecurityUser user) {
		
		    //String userid = user.getMember().getMemberID();
		    
		    QBasket basket = QBasket.basket;
			QBook qbook = QBook.book;
			
			JPQLQuery<CusBasketVO> query = from(basket)
		    		.innerJoin(basket.book, qbook)
		    		.where(basket.member.eq(user.getMember()))
		    		.select(Projections.bean(CusBasketVO.class,
		    								 qbook.bookCode,
	    									 qbook.bookTitle,
	    									 qbook.author,
	    									 qbook.publisher,
	    									 qbook.pubDate,
	    									 qbook.price,
	    									 qbook.barcode,
	    									 basket.orderQty,
	    									 basket.regdate));
		    		
		   return query.fetch();
		  
	}

	
}
