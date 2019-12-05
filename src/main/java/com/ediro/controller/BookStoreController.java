package com.ediro.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookStore")
public class BookStoreController {
   @GetMapping("/main")
   public void main() {
	   
   }
   @GetMapping("/joinMember")
   public void joinMember() {
	   
   }
   @GetMapping("/index")
   public void index() {
	   
   }
  
}

