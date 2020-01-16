package com.ediro.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ediro.service.BookOrderService;

import com.ediro.domain.Book;
import com.ediro.vo.BookBascketVO;
import com.ediro.vo.BookVO;
import com.ediro.vo.PageMaker;
import com.ediro.vo.PageVO;

@Controller
@RequestMapping("/bookStore")
public class BookStoreController {
	@Autowired
	   BookOrderService bookSrv;
	  
	@GetMapping("/main")
   public void main() {
	   
   }
  
   @GetMapping("/index")
   public void index() {
	   
   }
  
   @GetMapping("/bookList")
   public void bookList(Principal principal,Model model,PageVO pageVO,@ModelAttribute("book")BookVO book) {
	   
	   Page<BookBascketVO> booklist = bookSrv.getBooksWithBascketInfo(pageVO, book, principal);
	   model.addAttribute("booklist",new PageMaker<BookBascketVO>(booklist));
   }
}

