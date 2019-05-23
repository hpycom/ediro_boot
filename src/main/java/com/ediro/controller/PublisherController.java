package com.ediro.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ediro.domain.Book;
import com.ediro.persistence.BookRepository;
import com.ediro.service.BookService;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/publisher")
@Log
public class PublisherController {
   @Autowired
   BookService bookSrv;
   
   @GetMapping("/main")
   public void main() {
	   
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
}