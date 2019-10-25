package com.ediro.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Controller
@Log
public class LoginController {
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
   @GetMapping("/login")
   public String login(HttpServletRequest arg0, HttpServletResponse arg1, Authentication authentication) {
	   log.info("## request index page");
       if(authentication != null)
       {
       Collection <? extends GrantedAuthority> authorities = authentication.getAuthorities();
      
					authorities.forEach(authority -> {
								
							  if(authority.getAuthority().equals("ROLE_BOOKSTORE")) {
									try {
												redirectStrategy.sendRedirect(arg0,arg1,"/bookStore/main");
										  } catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
								} else if(authority.getAuthority().equals("ROLE_PUBLISHER")) {
										try {
													redirectStrategy.sendRedirect(arg0,arg1, "/publisher/main");
											  } catch (Exception e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
											  }
								} else {
						            throw new IllegalStateException();
								}
			     
			    });
       }
		   return "index";
   }
   @GetMapping("/accessDenied")
   public void accessDenied() {
	   
   }
   @GetMapping("/logout")
   public void logout() {
	   
   }
}
