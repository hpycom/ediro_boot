package com.ediro.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ediro.domain.Basket;
import com.ediro.domain.Book;
import com.ediro.domain.Member;
import com.ediro.domain.QBasket;
import com.ediro.domain.QBook;
import com.ediro.security.EdiroSecurityUser;
import com.querydsl.jpa.JPQLQuery;
@Repository
public class CustomBascketRepositoryImpl  extends QuerydslRepositorySupport implements CustomBascketRepository {

	public CustomBascketRepositoryImpl() {
		super(Basket.class);
	}
	
	@Override
	public List<Basket> search(Member member,@AuthenticationPrincipal EdiroSecurityUser user) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    String userid = user.getMember().getMemberID();
		    
		    QBasket bascket = QBasket.basket;
			 
		   JPQLQuery<Basket> query = from(bascket);
		  
		   if(StringUtils.hasText(userid))
		   {
			   query.where(bascket.member.memberID.eq(userid));
		   }
		   
			
		   List<Basket> result = query.fetch();
		   
		   return result;
	}

}
