package com.ediro.service;

import java.security.Principal;
import java.util.Optional;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.ediro.domain.Basket;
import com.ediro.domain.Book;
import com.ediro.domain.Member;
import com.ediro.persistence.BasketRepository;
import com.ediro.persistence.BookRepository;
import com.ediro.persistence.CustomBascketRepository;
import com.ediro.persistence.MemberRepository;
import com.ediro.security.EdiroSecurityUser;
import com.ediro.vo.BasketsVO;


@Service
public class BookOrderService {
	@Autowired
	CustomBascketRepository cBasketRepo;
	@Autowired
	BasketRepository basketRepo;
	@Autowired
	BookRepository bookRepo;
	@Autowired
	MemberRepository memberRepo;
	
	@Transactional
	public BasketsVO SaveBascket(BasketsVO bookBascket,@AuthenticationPrincipal EdiroSecurityUser user)
	{
		bookBascket.getListBascket().forEach(basketvo ->{
			Basket basket = new Basket();
		    Book book=	bookRepo.findBybookCode(basketvo.getBook_code());
		    Member mem=	memberRepo.findById(user.getMember().getMemberID()).orElse(null);
			
		    basket.setBook(book);
			basket.setMember(mem);
			basket.setOrderQty(basketvo.getOrderQty());
		   
			basketRepo.save(basket);
		    basketvo.setBasket_id(basket.getBasket_id()); 
			/*String book_code = bookInBascket.getBook().getBookCode().toString();
			Basket basket = bascketRepo.findOneByBookCode(book_code);
			bookInBascket.setBascket_id(bascket.getBascket_id());*/
		});
		
		return bookBascket;
	}
}
