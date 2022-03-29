package com.ediro.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
import com.ediro.vo.BookBascketVO;
import com.ediro.vo.BookSchRstVO;
import com.ediro.vo.BookVO;
import com.ediro.vo.CusBasketVO;
import com.ediro.vo.CusTempBasketVO;
import com.ediro.vo.TempBasketVO;
import com.ediro.vo.TempBasketsVO;

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
	public ResponseEntity<List<Book>> getBookList()
	{
	   List<Book> bookList = bookService.getBooks();
	   
	   return new ResponseEntity<>(bookList,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/schBooks", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<List<BookSchRstVO>> getBookList(@ModelAttribute("BookVO") BookVO vbook,Principal principal)
	{
		log.info(vbook.getBookTitle());
		
	   List<BookBascketVO> bookList = bookSrvCus.getBooks(vbook,principal);
	   
	   List<BookSchRstVO> lstBookSchRst = new ArrayList<BookSchRstVO>();
	   
	   for(BookBascketVO bookone :bookList)
	   {
		   BookSchRstVO bsrv = new BookSchRstVO();
		   bsrv.setBookCode(bookone.getBookCode());
		   bsrv.setBookTitle(bookone.getBookTitle());
		   bsrv.setPublisher(bookone.getPublisher());
		   bsrv.setAuthor(bookone.getAuthor());
		   bsrv.setPrice(bookone.getPrice());
		   bsrv.setBarcode(bookone.getBarcode());
		   bsrv.setPubDate(bookone.getPubDate());
		   
		   log.info(bookone.getBookTitle() + ": " + bookone.getDcPercent());
		   if(bookone.getCusPercent() >0)
		   {
			   bsrv.setSalePercent(bookone.getCusPercent());
		   }
		   else
			   bsrv.setSalePercent(bookone.getDcPercent());
		   
		   bsrv.setBookstatus(bookone.getBookstatus().getCode());
			   
		   lstBookSchRst.add(bsrv);
	   }
	   
	   return new ResponseEntity<>(lstBookSchRst,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveBasket", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<BasketsVO> saveBascket(@RequestBody BasketsVO vbascket,@AuthenticationPrincipal EdiroSecurityUser user )
	{
		//log.info(vbascket.getListBascket().get(0).getBook_code());
		
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
	   basketvo.setSalePercent(basketvo.getSalePercent());
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
	
	@GetMapping("/listTempBasket")
	public ResponseEntity<List<CusTempBasketVO>> listTempBascket(@AuthenticationPrincipal EdiroSecurityUser user)
	{
		
		
	   List<CusTempBasketVO> lbasket = bookOrdSrv.getListTempBasket(user);
	   
	   return new ResponseEntity<>(lbasket,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveTempBasket", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<TempBasketsVO> saveTempBasket(@RequestBody TempBasketsVO vbascket,@AuthenticationPrincipal EdiroSecurityUser user )
	{
		//log.info(vbascket.getListTempBascket().get(0).getBookCode().toString());
		
		TempBasketsVO bascketsVO = bookOrdSrv.SaveTempBascket(vbascket, user);
	   
	   return new ResponseEntity<>(bascketsVO,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delTempBasket", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<TempBasketsVO> delTempBasket(@RequestBody TempBasketsVO vbascket,@AuthenticationPrincipal EdiroSecurityUser user )
	{
		//log.info(vbascket.getListTempBascket().get(0).getBookCode().toString());
		
		TempBasketsVO bascketsVO = bookOrdSrv.DelTempBascket(vbascket, user);
	   
	   return new ResponseEntity<>(bascketsVO,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/clearTempBasket", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void clearTempBasket(@AuthenticationPrincipal EdiroSecurityUser user )
	{
		//log.info(vbascket.getListTempBascket().get(0).getBookCode().toString());
		
		bookOrdSrv.DelTempBascketAll(user);
	   
	   //return new ResponseEntity<>(bascketsVO,HttpStatus.OK);
	}
}
