package com.ediro.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ediro.domain.Book;
import com.ediro.persistence.BookRepository;
import com.ediro.service.BookService;
import com.ediro.vo.BooksVO;
import com.ediro.vo.PageMaker;
import com.ediro.vo.PageVO;

import lombok.extern.java.Log;
import java.security.Principal;

@Controller
@RequestMapping("/publisher")
@Log
public class PublisherController {
   @Autowired
   BookService bookSrv;
   
   @GetMapping("/main")
   public void main(Principal principal,Model model,PageVO pageVO,@ModelAttribute("book")Book book) {
	   
	 
	   Page<Book> result = bookSrv.getBooks(pageVO,book);
	   
	  // List<Book> bookList = bookSrv.getBooks(principal.getName());	
	   
	   
	   model.addAttribute("result",new PageMaker<Book>(result));
   }
   
   @GetMapping("/addBook")
   public void addBook(@ModelAttribute("book")Book book) {
	   log.info(book.toString());
   }
  
   @PostMapping("/insertBook")
   public String insertBook(@ModelAttribute("book")Book book,RedirectAttributes rttr,Principal principal) {
	   log.info("" + book);
	   bookSrv.save(book,principal);
	   return "redirect:/publisher/addBook";
   }
   

 // @PostMapping("/updBooks")
   
   @RequestMapping(value="updBooks",  method= RequestMethod.POST,consumes="application/json")
   public @ResponseBody  String updBooks( @RequestBody  BooksVO name,RedirectAttributes rttr,Principal principal) {
		   log.info("" + name);
		   bookSrv.save(name, principal);
		   return "asdf";
		 
   }
	   
}