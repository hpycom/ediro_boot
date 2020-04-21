package com.ediro.controller;

import lombok.extern.java.Log;

import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ediro.domain.Member;
import com.ediro.persistence.MemberRepository;
import com.ediro.service.MemberService;
/**
 * @author hpycom
 * @Date 2018-06-04
 * @GitHub : https://github.com/
 */
@Controller
@Log
public class IndexController {
	
	@Autowired
	private MemberService memService;
	@Autowired
	private MemberRepository memRepo;
	
	
    @GetMapping("/")
    public String index(HttpServletRequest arg0, HttpServletResponse arg1, Authentication authentication) {
        log.info("## request index page");
      /*  if(authentication != null)
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
        }*/
		   return "index";
    }

    @RequestMapping(value ="/index",method=RequestMethod.POST)
    public String index() {
    	return "index";
    }
    
    @GetMapping("/joinMember")
    public ModelAndView joinMember(@ModelAttribute("Member") Member member) {
    	return new ModelAndView("joinMember","member",member);
    }
    
    @PostMapping("/joinMember_save")
    public String joinMember_save(@ModelAttribute("Member") Member member)
    {
        memService.saveNewMember(member);
    	
    	return "redirect:/login";

    } 
  
    @RequestMapping(value ="/isMember/{name}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public  @ResponseBody String isMember(@PathVariable("name") String name)
    {
      String result = "0";
      Optional<Member> member =	memRepo.findByMemberID(name);
    
      if(member.isPresent())
    	  result ="1";
      
      return result;
    }
 
}