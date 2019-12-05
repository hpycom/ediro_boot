package com.ediro.controller;

import lombok.extern.java.Log;

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
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author hpycom
 * @Date 2018-06-04
 * @GitHub : https://github.com/
 */
@Controller
@Log
public class IndexController {
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
    @GetMapping("/")
    public String index(HttpServletRequest arg0, HttpServletResponse arg1, Authentication authentication) {
        log.info("## request index page");
        if(authentication != null)
        {
        Collection <? extends GrantedAuthority> authorities = authentication.getAuthorities();
       
					authorities.forEach(authority -> {
								
							 if(authority.getAuthority().equals("ROLE_BOOKSTORE")) {
								/*	try {
												redirectStrategy.sendRedirect(arg0,arg1,"/bookStore/main");
										  } catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}*/
								
								} else 
									
									if(authority.getAuthority().equals("ROLE_PUBLISHER")) {
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

    @RequestMapping(value ="/index",method=RequestMethod.POST)
    public String index() {
    	return "index";
    }
 
}