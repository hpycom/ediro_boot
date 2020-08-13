package com.ediro.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.ediro.vo.CusTempBasketVO;
import com.ediro.vo.PageVO;
import com.ediro.vo.TempBasketVO;
import com.ediro.vo.TempBasketsVO;


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
	
	
	
	@Transactional(rollbackFor = {Exception.class})
	public Basket SaveBascket(BasketVO bookBascket,@AuthenticationPrincipal EdiroSecurityUser user)
	{
			// Member mem=	memberRepo.findByMemberID(user.getMember().getMemberID()).orElse(null);
		
		 
		Basket _basket = basketRepo.findOneByBook_bookCodeAndMember_mid(bookBascket.getBook_code(),user.getMember().getMid());
	
		if(_basket == null)
		{
			Book book=	bookRepo.findBybookCode(bookBascket.getBook_code());
				
			Basket basket = new Basket();
		  	
		    basket.setBook(book);
			basket.setMember(user.getMember());
			basket.setSalePercent(bookBascket.getSalePercent());
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
	
	@Transactional(rollbackFor = {Exception.class})
	public BasketsVO SaveBascket(BasketsVO bookBascket,@AuthenticationPrincipal EdiroSecurityUser user)
	{
		bookBascket.getListBascket().forEach(basketvo ->{
			 
			
			 
			 Basket _basket = basketRepo.findOneByBook_bookCodeAndMember_mid(basketvo.getBook_code(),user.getMember().getMid());
		
			if(_basket == null)
			{
				Book book=	bookRepo.findBybookCode(basketvo.getBook_code());
				
				Basket tbasket = new Basket();
			  	
			    tbasket.setBook(book);
				tbasket.setMember(user.getMember());
				tbasket.setSalePercent(basketvo.getSalePercent());
				tbasket.setOrderQty(basketvo.getOrderQty());
			   
				basketRepo.save(tbasket);
				
				
				
			}
			else
			{
				_basket.setOrderQty(_basket.getOrderQty() + basketvo.getOrderQty());
				basketRepo.save(_basket);
				
			
				
			}
			
			TempBasket tempbasket = tempBasketRepo.findOneByBook_bookCodeAndMember_mid(basketvo.getBook_code(),user.getMember().getMid());
			
			tempBasketRepo.delete(tempbasket);
		});
		
		return bookBascket;
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
	
	public List<CusTempBasketVO> getListTempBasket(@AuthenticationPrincipal EdiroSecurityUser user)
	{		
		return tempBasketRepo.search(user);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public TempBasket SaveTempBascket(TempBasketVO bookBascket,@AuthenticationPrincipal EdiroSecurityUser user)
	{
		 Book book=	bookRepo.findBybookCode(bookBascket.getBookCode());
		 Member mem=	memberRepo.findByMemberID(user.getMember().getMemberID()).orElse(null);
		
		 
		 TempBasket _basket = tempBasketRepo.findOneByBook_bookCodeAndMember_mid(book.getBookCode(),mem.getMid());
	
		if(_basket == null)
		{
			TempBasket tbasket = new TempBasket();
		  	
		    tbasket.setBook(book);
			tbasket.setMember(mem);
			tbasket.setSalePercent(bookBascket.getSalePercent());
			tbasket.setOrderQty(bookBascket.getQty());
		   
			tempBasketRepo.save(tbasket);
			
			return tbasket;
		}
		else
		{
			_basket.setOrderQty(_basket.getOrderQty() + bookBascket.getQty());
			tempBasketRepo.save(_basket);
			return _basket;
		}
		
	
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public TempBasketsVO SaveTempBascket(TempBasketsVO bookBascket,@AuthenticationPrincipal EdiroSecurityUser user)
	{
		bookBascket.getListTempBascket().forEach(basketvo ->{
			 
			
			 
			 TempBasket _basket = tempBasketRepo.findOneByBook_bookCodeAndMember_mid(basketvo.getBookCode(),user.getMember().getMid());
		
			if(_basket == null)
			{
				Book book=	bookRepo.findBybookCode(basketvo.getBookCode());
				
				TempBasket tbasket = new TempBasket();
			  	
			    tbasket.setBook(book);
				tbasket.setMember(user.getMember());
				tbasket.setSalePercent(basketvo.getSalePercent());
				tbasket.setOrderQty(basketvo.getQty());
			   
				tempBasketRepo.save(tbasket);
				
				
			}
			else
			{
				_basket.setOrderQty(basketvo.getQty());
				_basket.setSalePercent(basketvo.getSalePercent());
				tempBasketRepo.save(_basket);
				
			}
		});
		
		return bookBascket;
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public TempBasketsVO DelTempBascket(TempBasketsVO bookBascket,@AuthenticationPrincipal EdiroSecurityUser user)
	{
		bookBascket.getListTempBascket().forEach(basketvo ->{
			
			TempBasket _basket = tempBasketRepo.findOneByBook_bookCodeAndMember_mid(basketvo.getBookCode(),user.getMember().getMid());
		
			tempBasketRepo.delete(_basket);
		});
		
		return bookBascket;
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void DelTempBascketAll(@AuthenticationPrincipal EdiroSecurityUser user)
	{
		tempBasketRepo.deleteAll(tempBasketRepo.findAllByMember_mid(user.getMember().getMid()));
		
	}
}
