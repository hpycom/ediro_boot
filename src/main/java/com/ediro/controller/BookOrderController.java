package com.ediro.controller;

import lombok.extern.java.Log;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ediro.domain.Book;
import com.ediro.domain.Member;
import com.ediro.persistence.BookRepository;
import com.ediro.security.EdiroSecurityUser;
import com.ediro.service.BookService;
import com.ediro.vo.BooksVO;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookOrder")
@Log
public class BookOrderController {
	
	//@Autowired
	//BookRepository bookrepo;
	@Autowired BookService bookService;
	
	

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
	   
	   List<Book> bookList = bookService.getBooks(user.getMember().getMemberID());	 //(List<Book>) bookrepo.findAll();
	
	   return new ResponseEntity<>(bookList,HttpStatus.OK);
	}
	
	
}
