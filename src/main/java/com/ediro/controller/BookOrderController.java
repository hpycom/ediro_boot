package com.ediro.controller;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ediro.domain.Book;
import com.ediro.persistence.BookRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookOrder")
@Log
public class BookOrderController {
	
	@Autowired
	BookRepository bookrepo;
	
	
	@GetMapping("/{bookTitle}")
	public ResponseEntity<List<Book>> getBookByTitle(@PathVariable("bookTitle") String bookTitle)
	{
		log.info("get books by titles");
	   List<Book> bookList =	bookrepo.findByBookTitleContaining(bookTitle);
	
	   return new ResponseEntity<>(bookList,HttpStatus.OK);
	}

	@GetMapping("/booklist")
	public ResponseEntity<List<Book>> getBookList()
	{
		log.info("get books list");
	   List<Book> bookList =	(List<Book>) bookrepo.findAll();
	
	   return new ResponseEntity<>(bookList,HttpStatus.OK);
	}

}
