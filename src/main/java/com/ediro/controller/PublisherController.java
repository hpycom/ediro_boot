package com.ediro.controller;

import java.io.File;
import java.security.Principal;



import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ediro.domain.Book;

import com.ediro.service.BookService;
import com.ediro.vo.BooksVO;
import com.ediro.vo.PageMaker;
import com.ediro.vo.PageVO;


import lombok.extern.java.Log;


@Controller
@RequestMapping("/publisher")
@Log
public class PublisherController {
   
   @Value("${bookimage.uploaded}")
   private  String UPLOADED_FOLDER;
	
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
  
   @RequestMapping(value = "/insertBook", method = RequestMethod.POST)
   public String insertBook(@RequestParam("imageFile") MultipartFile imageFile, @ModelAttribute("book")Book book,Principal principal) throws Exception{
	
	   bookSrv.save(book,principal);
	   if(!imageFile.isEmpty()){
		      String sourceFileName = imageFile.getOriginalFilename(); 
		        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); 
		        File destinationFile = new File(UPLOADED_FOLDER + book.getBarcode()+"."+sourceFileNameExtension); 
		           log.info("" + destinationFile);
		    
		           destinationFile.getParentFile().mkdirs(); 
			        imageFile.transferTo(destinationFile);
		       
			       

	   }

	   return "redirect:/publisher/addBook";
	 
   }
   

 // @PostMapping("/updBooks")
   
   @RequestMapping(value="updBooks",  method= RequestMethod.POST,consumes="application/json")
   public @ResponseBody  String updBooks( @RequestBody  BooksVO name,RedirectAttributes rttr,Principal principal) {
		   log.info("" + name);
		   bookSrv.save(name, principal);
		   return "asdf";
		 
   }
   @GetMapping("/bookMaster")
   public void bookMaster() {
	 
   }   
}