package com.ediro.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.ediro.domain.Basket;
import com.ediro.domain.QBasket;
import com.ediro.domain.QBook;
import com.ediro.domain.QTempBasket;
import com.ediro.domain.TempBasket;
import com.ediro.security.EdiroSecurityUser;
import com.ediro.vo.CusBasketVO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

public class TempBasketRepositoryImpl  extends QuerydslRepositorySupport implements TempBasketRepositoryCustom  {

	public TempBasketRepositoryImpl() {
		super(TempBasket.class);
	}
	
	@Override
	public List<CusBasketVO> search(@AuthenticationPrincipal EdiroSecurityUser user) {
		
		    //String userid = user.getMember().getMemberID();
		    
		QTempBasket tbasket = QTempBasket.tempBasket;
			QBook qbook = QBook.book;
			
			JPQLQuery<CusBasketVO> query = from(tbasket)
		    		.innerJoin(tbasket.book, qbook)
		    		.where(tbasket.member.eq(user.getMember()))
		    		.select(Projections.bean(CusBasketVO.class,
		    								 qbook.bookCode,
	    									 qbook.bookTitle,
	    									 qbook.author,
	    									 qbook.publisher,
	    									 qbook.pubDate,
	    									 qbook.price,
	    									 qbook.barcode,
	    									 tbasket.orderQty,
	    									 tbasket.regdate));
		    		
		   return query.fetch();
		  
	}
}
