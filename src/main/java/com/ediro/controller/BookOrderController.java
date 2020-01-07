package com.ediro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ediro.domain.Basket;
import com.ediro.domain.Book;
import com.ediro.persistence.BasketRepository;
import com.ediro.security.EdiroSecurityUser;
import com.ediro.service.BookOrderService;
import com.ediro.service.BookService;
import com.ediro.service.BookServiceCustom;
import com.ediro.vo.BasketVO;
import com.ediro.vo.BasketsVO;
import com.ediro.vo.BookVO;
import com.ediro.vo.CusBasketVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/bookOrder")
@Log
public class BookOrderController {
	
	//@Autowired
	//BookRepository bookrepo;
	@Autowired BookService bookService;
	@Autowired 
	BookServiceCustom bookSrvCus;
	@Autowired
	BookOrderService bookOrdSrv;
    @Autowired
    BasketRepository basRepo;
    
	@GetMapping("/booklist")
	public ResponseEntity<List<Book>> getBookList(@AuthenticationPrincipal EdiroSecurityUser user)
	{
	   List<Book> bookList = bookService.getBooks();
	   
	   return new ResponseEntity<>(bookList,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/schBooks", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<List<Book>> getBookList(@ModelAttribute("BookVO") BookVO vbook)
	{
		log.info(vbook.getBookTitle());
		
	   List<Book> bookList = bookSrvCus.getBooks(vbook);
	   
	   return new ResponseEntity<>(bookList,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveBasket", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<BasketsVO> saveBascket(@RequestBody BasketsVO vbascket,@AuthenticationPrincipal EdiroSecurityUser user)
	{
		log.info(vbascket.getListBascket().get(0).getBook_code().toString());
		
	   BasketsVO bascketsVO = bookOrdSrv.SaveBascket(vbascket,user);
	   
	   return new ResponseEntity<>(bascketsVO,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveBasketOne", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<BasketVO> saveBascketOne(@RequestBody BasketVO bascketvo,@AuthenticationPrincipal EdiroSecurityUser user)
	{
		
	   Basket bascket = bookOrdSrv.SaveBascket(bascketvo, user);
	   BasketVO basketvo = new BasketVO();
	  
	   basketvo.setMid(bascket.getMember().getMid());
	   basketvo.setBook_code(bascket.getBook().getBookCode());
	   basketvo.setOrderQty(bascket.getOrderQty());
	   
	   
	   
	   return new ResponseEntity<>(basketvo,HttpStatus.OK);
	}
	
	//@RequestMapping(value = "/listBasket", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	//@ResponseBody
	/*@GetMapping("/listBasket")
	public ResponseEntity<List<Basket>> listBascket(@AuthenticationPrincipal EdiroSecurityUser user)
	{
		
		
	   List<Basket> lbasket = basRepo.findAllByMember_memberID(user.getUsername());
	   
	   return new ResponseEntity<>(lbasket,HttpStatus.OK);
	}
	*/
	@GetMapping("/listBasket")
	public ResponseEntity<List<CusBasketVO>> listBascket(@AuthenticationPrincipal EdiroSecurityUser user)
	{
		
		
	   List<CusBasketVO> lbasket = bookOrdSrv.getListBasket(user);
	   
	   return new ResponseEntity<>(lbasket,HttpStatus.OK);
	}
}
