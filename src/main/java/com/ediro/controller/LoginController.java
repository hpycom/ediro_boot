package com.ediro.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
   @GetMapping("/login")
   public void login() {
	   
   }
   @GetMapping("/accessDenied")
   public void accessDenied() {
	   
   }
   @GetMapping("/logout")
   public void logout() {
	   
   }
}
