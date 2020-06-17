package com.ediro.controller;

import java.io.File;
import java.math.BigInteger;


import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.ediro.domain.MemberBookDiscount;
import com.ediro.security.EdiroSecurityUser;
import com.ediro.service.BookService;
import com.ediro.service.MemberBookDiscountService;
import com.ediro.vo.BooksVO;
import com.ediro.vo.MemberBookDiscountVO;
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
	
	@Autowired
	MemberBookDiscountService mBookDisSrv;
   
	 @Autowired
	    private HttpServletRequest request;
	 
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

    @RequestMapping(value = "/getBook", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<Book> getBookBybookCode(@RequestParam("book_code") String book_code)
	{
	    BigInteger _book_code = new BigInteger(book_code);
        Book book = bookSrv.getBookByBookCode(_book_code);
        return new ResponseEntity<>(book,HttpStatus.OK);
	}
  
   @RequestMapping(value = "/getMemBookDiscnt", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  	@ResponseBody
  	public ResponseEntity<List<MemberBookDiscountVO>> getMemBookDiscnt(@RequestParam("book_code") String book_code)
  	{
  	    BigInteger _book_code = new BigInteger(book_code);
         List<MemberBookDiscountVO> listMemberBookDiscnt =  mBookDisSrv.getMembookDiscounts(_book_code);
          return new ResponseEntity<>(listMemberBookDiscnt,HttpStatus.OK);
  	}
   
   @RequestMapping(value = "/insertBook", method = RequestMethod.POST)
   public String insertBook(@RequestParam("imageFile") MultipartFile imageFile, @ModelAttribute("book")Book book,Principal principal) throws Exception{
	
	   bookSrv.save(book,principal);
	   if(!imageFile.isEmpty()){
		     // String sourceFileName = imageFile.getOriginalFilename(); 
		        //String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); 
		        File destinationFile = new File(UPLOADED_FOLDER + book.getBarcode()+".jpg"); 
		           log.info("" + destinationFile);
		    
		           destinationFile.getParentFile().mkdirs(); 
			        imageFile.transferTo(destinationFile);
		       
			       

	   }

	   return "redirect:/publisher/addBook";
	 
   }
   
   @RequestMapping(value = "/updateBookCover", method = RequestMethod.POST)
   public  ResponseEntity<?> updateBookCover(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam("barcode") String barcode,Principal principal,HttpServletResponse response) throws Exception{
	try
	{
	  if(!imageFile.isEmpty()){
		     // String sourceFileName = imageFile.getOriginalFilename(); 
		        //String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); 
		  
		       // File destinationFile = new File(UPLOADED_FOLDER + barcode+".jpg"); 
		        //   log.info("" + destinationFile);
		    
		          // destinationFile.getParentFile().mkdirs(); 
			      //  imageFile.transferTo(destinationFile);
		       
			       // String uploadsDir = "/upload/";
                    String realPathtoUploads =  UPLOADED_FOLDER;
                    
                    log.info(realPathtoUploads+  barcode+".jpg");
                    
                    if(! new File(realPathtoUploads).exists())
                    {
                        new File(realPathtoUploads).mkdir();
                    }

                   


                    //String orgName = imageFile.getOriginalFilename();
                    String filePath = realPathtoUploads +  barcode+".jpg";
                    File dest = new File(filePath);
                    imageFile.transferTo(dest);
			       

	   }
	} catch (Exception ex){
		  log.info("" + ex.getMessage()+"!!!");
	   	 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}
	  return new ResponseEntity("Successfully uploaded - " + imageFile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
	
   }

 // @PostMapping("/updBooks")
   @Secured(value= {"ROLE_PUBLISHER","ROLE_BOOKSTORE"})
   @RequestMapping(value="updBooks",  method= RequestMethod.POST,consumes="application/json")
   public @ResponseBody  String updBooks( @RequestBody  BooksVO name,RedirectAttributes rttr,Principal principal) {
		   log.info("" + name);
		   bookSrv.save(name, principal);
		   return "asdf";
		 
   }
   @GetMapping("/bookMaster")
   public void bookMaster(Model model) {
	   model.addAttribute("UPLOADED_FOLDER",UPLOADED_FOLDER);
   }   
}