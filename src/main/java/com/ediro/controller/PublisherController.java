package com.ediro.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/publisher")
public class PublisherController {
   @GetMapping("/main")
   public void main() {
	   
   }
   @GetMapping("/addBook")
   public void addBook() {
	   
   }
}