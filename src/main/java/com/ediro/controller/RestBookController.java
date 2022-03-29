package com.ediro.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ediro.domain.Book;
import com.ediro.domain.MemberBookDiscount;
import com.ediro.security.EdiroSecurityUser;
import com.ediro.service.BookService;
import com.ediro.service.MemberBookDiscountService;
import com.ediro.vo.BooksVO;
import com.ediro.vo.MemberBookDiscountVO;
import com.ediro.vo.MemberBookDiscountsVO;

import lombok.extern.java.Log;
@Log
@RestController

@RequestMapping("/data/book")
public class RestBookController {

	@Autowired
	BookService bookSrv;
		
	@Autowired
	MemberBookDiscountService mBookDisSrv;
		
	 @RequestMapping(value = "/getBook", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	 @PreAuthorize("isAuthenticated() and hasRole('ROLE_PUBLISHER')")
		public Book getBookBybookCode(@RequestParam("book_code") String book_code, HttpServletResponse response) throws IOException
		{
		 Book book = null;
		 try {
			
		    long _book_code = Long.parseLong(book_code);
		   
		     book = bookSrv.getBookByBookCode(_book_code);
		   
		    }  catch (Exception ex)
		 {
		    	 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		 }
		 return book;
		}
	
		 @RequestMapping(value="updBook",  method= RequestMethod.POST,consumes="application/json")
		   public @ResponseBody  String updBooks( @RequestBody  Book book,RedirectAttributes rttr,Principal principal) {
				   log.info("" + book);
				   bookSrv.save(book, principal);
				   return "asdf";
				 
		   }
	 
		 @RequestMapping(value="delBook",  method= RequestMethod.POST,consumes="application/json")
		   public @ResponseBody  String delBooks(@RequestBody  Book book,RedirectAttributes rttr,Principal principal) {
			 long book_code =book.getBookCode();
				   bookSrv.deleteBook(book_code,principal);
				   return "asdf";
				 
		   }
		 
	    @RequestMapping(value = "/getMemBookDiscnt", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	  	public List<MemberBookDiscountVO> getMemBookDiscnt(@RequestParam("book_code") String book_code)
	  	{
	  	    BigInteger _book_code = new BigInteger(book_code);
	         List<MemberBookDiscountVO> listMemberBookDiscnt =  mBookDisSrv.getMembookDiscounts(_book_code);
	         return listMemberBookDiscnt;
	  	}
	    
	    @RequestMapping(value = "/saveMemBookDiscnt", method = RequestMethod.POST,consumes="application/json")
	  	public  @ResponseBody String saveMemBookDiscnt(@RequestParam("book_code") String book_code,@RequestBody MemberBookDiscountsVO membookDiscounts)
	  	{
	    	 long _book_code = Long.parseLong(book_code);
	        mBookDisSrv.saveMembookDiscounts(_book_code, membookDiscounts);
	        return "asdf";
	         
	  	}
	    
	    @RequestMapping(value = "/DelMemBookDiscnt", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	  	public void DelMemBookDiscnt(@RequestParam("book_code") String book_code,@RequestParam("mid") int mid, HttpServletResponse response)throws IOException
	  	{
	    	try
	    	{
	  	    BigInteger _book_code = new BigInteger(book_code);
	        mBookDisSrv.DelMembookDiscounts(_book_code, mid);
	    	} 
	    	 catch (Exception ex)
			 {
			    	 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			 }
	         
	  	}
	    
	    @RequestMapping(value = "/existMemBook", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	  	public boolean existsMemBookDiscnt(@RequestParam("book_code") String book_code,@RequestParam("mid") int mid)
	  	{
	  	    BigInteger _book_code = new BigInteger(book_code);
	       boolean existMemBookDiscount  =  mBookDisSrv.getMemberBookDisCount(_book_code, mid);
	       
	    		   return existMemBookDiscount;
	  	}
	    
	    @RequestMapping(value = "/addMemBook", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	  	public void existsMemBookDiscnt(@RequestParam("book_code") String book_code,@RequestParam("mid") int mid,@RequestParam("disPct") int disPct, HttpServletResponse response)throws IOException
	  	{
	    	 try {
	    		
	    		 long _book_code = Long.parseLong(book_code);
	    		 mBookDisSrv.saveMemBookDiscount(_book_code, mid, disPct);
	    	 } 
	    	 catch (Exception ex)
			 {
			    	 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			 }
	  	}
}
