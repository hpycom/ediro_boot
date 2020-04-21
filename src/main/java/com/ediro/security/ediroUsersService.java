package com.ediro.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//import org.springframework.test.annotation.IfProfileValue;
import org.springframework.transaction.annotation.Transactional;

import com.ediro.domain.Member;
import com.ediro.domain.MemberRole;
import com.ediro.persistence.MemberRepository;
//import org.zerock.security.ZerockSecurityUser;
import com.ediro.service.MemberService;

import javax.xml.ws.ServiceMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author zacconding
 * @Date 2017-12-24
 * @GitHub : https://github.com/zacscoding
 */
@Service
@Log
public class ediroUsersService implements UserDetailsService{
	@Autowired
    private MemberRepository memberRepository;
	 private static final String ROLE_PREFIX="ROLE_";
	 
	    @Autowired
	    MemberService memService;
	
	 
	@Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	   
		  log.info("## request loadUserByUserName userName : " + username);
		Optional<Member> mem =  memberRepository.findByMemberID(username);
		
		   return   memberRepository.findByMemberID(username).filter(m->m!=null).map(m-> new EdiroSecurityUser(m, makeGrantedAuthority(m))).get();
	       
	    }
	/*
	 @Override
	    public UserDetails loadUserByUsername(String userName)
	      throws UsernameNotFoundException {
	  
		 Optional<Member> mem = memberRepository.findByMemberID(userName);
		 
		 return  memberRepository.findByMemberID(userName).filter(m->m!=null).map(m-> new org.springframework.security.core.userdetails.User(m.getMemberID(),  "{noop}" + m.getMemberPwd(), makeGrantedAuthority(m)) ).get();
	       
	    }
	*/
	 private List<GrantedAuthority> makeGrantedAuthority(Member member) {
	        List<GrantedAuthority> list = new ArrayList<>();
	        List<MemberRole> MemberRoles = memService.getMemRols(member);
	       
	        MemberRoles.forEach(memberRole -> {
	            list.add(new SimpleGrantedAuthority(ROLE_PREFIX + memberRole.getRoles().getRoleName()));
	        });

	        return list;
	    }


}
