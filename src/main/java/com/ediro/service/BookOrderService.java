package com.ediro.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.ediro.domain.Basket;
import com.ediro.domain.Book;
import com.ediro.domain.Member;
import com.ediro.domain.TempBasket;
import com.ediro.persistence.BasketRepository;
import com.ediro.persistence.BookRepository;

import com.ediro.persistence.MemberRepository;
import com.ediro.persistence.TempBasketRepository;
import com.ediro.security.EdiroSecurityUser;
import com.ediro.vo.BasketVO;
import com.ediro.vo.BasketsVO;
import com.ediro.vo.BookBascketVO;
import com.ediro.vo.BookVO;
import com.ediro.vo.CusBasketVO;
import com.ediro.vo.PageVO;


@Service
public class BookOrderService {
	
	@Autowired
	BasketRepository basketRepo;
	@Autowired
	BookRepository bookRepo;
	@Autowired
	MemberRepository memberRepo;
	@Autowired
	TempBasketRepository tempBasketRepo;
	
	@Transactional
	public BasketsVO SaveBascket(BasketsVO bookBascket,@AuthenticationPrincipal EdiroSecurityUser user)
	{
		bookBascket.getListBascket().forEach(basketvo ->{
			Basket basket = new Basket();
		    Book book=	bookRepo.findBybookCode(basketvo.getBook_code());
		    Member mem=	memberRepo.findByMemberID(user.getMember().getMemberID()).orElse(null);
			
		    basket.setBook(book);
			basket.setMember(mem);
			basket.setOrderQty(basketvo.getOrderQty());
		   
			basketRepo.save(basket);
		   /* basketvo.setBasket_id(basket.getBasket_id()); */
			/*String book_code = bookInBascket.getBook().getBookCode().toString();
			Basket basket = bascketRepo.findOneByBookCode(book_code);
			bookInBascket.setBascket_id(bascket.getBascket_id());*/
		});
		
		return bookBascket;
	}
	
	@Transactional
	public Basket SaveBascket(BasketVO bookBascket,@AuthenticationPrincipal EdiroSecurityUser user)
	{
		 Book book=	bookRepo.findBybookCode(bookBascket.getBook_code());
		 Member mem=	memberRepo.findByMemberID(user.getMember().getMemberID()).orElse(null);
		
		 
		Basket _basket = basketRepo.findOneByBook_bookCodeAndMember_mid(book.getBookCode(),mem.getMid());
	
		if(_basket == null)
		{
			Basket basket = new Basket();
		  	
		    basket.setBook(book);
			basket.setMember(mem);
			basket.setOrderQty(bookBascket.getOrderQty());
		   
			basketRepo.save(basket);
			
			return basket;
		}
		else
		{
			_basket.setOrderQty(_basket.getOrderQty() + bookBascket.getOrderQty());
			basketRepo.save(_basket);
			return _basket;
		}
		
	
	}
	
	public List<CusBasketVO> getListBasket(@AuthenticationPrincipal EdiroSecurityUser user)
	{		
		return basketRepo.search(user);
	}
	
	public Page<Book> getBooks(PageVO vo,Book book)
	{
		Pageable page = vo.makePageable(0, "bookCode");
		
		Page<Book> result = bookRepo.findAll(bookRepo.makePredicate(book), page);
		
		return result;
	}
	
	public Page<BookBascketVO> getBooksWithBascketInfo(PageVO vo,BookVO book,Principal principal)
	{
		Pageable page = vo.makePageable(0, "bookCode");
		
		Page<BookBascketVO> result = bookRepo.getBookListWithCartInfo(book,page,principal);
		
		return result;
	}
	
	@Transactional
	public TempBasket SaveTempBascket(BasketVO bookBascket,@AuthenticationPrincipal EdiroSecurityUser user)
	{
		 Book book=	bookRepo.findBybookCode(bookBascket.getBook_code());
		 Member mem=	memberRepo.findByMemberID(user.getMember().getMemberID()).orElse(null);
		
		 
		 TempBasket _basket = tempBasketRepo.findOneByBook_bookCodeAndMember_mid(book.getBookCode(),mem.getMid());
	
		if(_basket == null)
		{
			TempBasket tbasket = new TempBasket();
		  	
		    tbasket.setBook(book);
			tbasket.setMember(mem);
			tbasket.setOrderQty(bookBascket.getOrderQty());
		   
			tempBasketRepo.save(tbasket);
			
			return tbasket;
		}
		else
		{
			_basket.setOrderQty(_basket.getOrderQty() + bookBascket.getOrderQty());
			tempBasketRepo.save(_basket);
			return _basket;
		}
		
	
	}
}
