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

import com.ediro.domain.Book;
import com.ediro.security.EdiroSecurityUser;
import com.ediro.service.BookOrderService;
import com.ediro.service.BookService;
import com.ediro.service.BookServiceCustom;
import com.ediro.vo.BasketsVO;
import com.ediro.vo.BookVO;

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

	@GetMapping("/booklist")
	public ResponseEntity<List<Book>> getBookList(@AuthenticationPrincipal EdiroSecurityUser user)
	{
		/*Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserDetails userDetails = (UserDetails)principal;

	   String user_id = userDetails.getUsername();
	  
	   log.info("get books list");
	*/
		
	   /*Member imember = ((EdiroSecurityUser) userDetails).getMember();
	  	log.info(imember.getCompanyName());
	   	*/
	  // String user_id = "pubcom";
	   
	   //List<Book> bookList = bookService.getBooks(user.getMember().getMemberID());	 //(List<Book>) bookrepo.findAll();
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
		log.info(vbascket.getListBascket().get(0).getBasket_id().toString());
		
	   BasketsVO bascketsVO = bookOrdSrv.SaveBascket(vbascket,user);
	   
	   return new ResponseEntity<>(bascketsVO,HttpStatus.OK);
	}
	
}
