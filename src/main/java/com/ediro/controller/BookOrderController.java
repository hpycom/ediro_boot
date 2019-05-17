package com.ediro.controller;

import lombok.extern.java.Log;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ediro.domain.Book;
import com.ediro.domain.Member;
import com.ediro.persistence.BookRepository;
import com.ediro.security.EdiroSecurityUser;
import com.ediro.service.BookService;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookOrder")
@Log
public class BookOrderController {
	
	@Autowired
	BookRepository bookrepo;
	@Autowired BookService bookService;
	
	@GetMapping("/{bookTitle}")
	public ResponseEntity<List<Book>> getBookByTitle(@PathVariable("bookTitle") String bookTitle)
	{
	   log.info("get books by titles");
	   List<Book> bookList =	bookrepo.findByBookTitleContaining(bookTitle);
	
	   return new ResponseEntity<>(bookList,HttpStatus.OK);
	}

	@GetMapping("/booklist")
	public ResponseEntity<List<Book>> getBookList(Principal principal)
	{
		//Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserDetails userDetails = (UserDetails)principal;

	   String user_id = ((EdiroSecurityUser) principal).getMember().getCompanyName();// userDetails.getUsername();
	  
	   log.info("get books list");
	
	   Member imember = ((EdiroSecurityUser) userDetails).getMember();
	   	log.info(imember.getCompanyName());
	   	
	   List<Book> bookList = bookService.getBooks(user_id);	 //(List<Book>) bookrepo.findAll();
	
	   return new ResponseEntity<>(bookList,HttpStatus.OK);
	}

}
