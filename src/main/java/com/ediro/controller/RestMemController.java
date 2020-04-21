package com.ediro.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.ediro.domain.Member;
import com.ediro.service.MemberService;
import com.ediro.vo.MemberVO;
import lombok.extern.java.Log;

@Log
@RestController

@RequestMapping("/data/member")
public class RestMemController {

	@Autowired
	   MemberService memSrv;
		
		
		
	 @RequestMapping(value = "/getMember", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	 @PreAuthorize("isAuthenticated() and hasRole('ROLE_PUBLISHER')")
	
	public List<Member> getMemberByMemVo(@ModelAttribute("MemberVO") MemberVO memVo, HttpServletResponse response) throws IOException
	{
		 List<Member> mem = null;
		 try {
			 	mem = memSrv.getCusMembers(memVo);
		  
	      }  
		 catch (Exception ex){
		    	 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		 }
		 
		 return mem;
	
	}
	
	 
}
