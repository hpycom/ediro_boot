package com.ediro.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//import org.springframework.test.annotation.IfProfileValue;
import org.springframework.transaction.annotation.Transactional;

import com.ediro.persistence.MemberRepository;
//import org.zerock.security.ZerockSecurityUser;

import javax.xml.ws.ServiceMode;
import java.util.Arrays;

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
	
	@Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	   
		  log.info("## request loadUserByUserName userName : " + username);

	      return   memberRepository.findByMemberID(username).filter(m->m!=null).map(m-> new EdiroSecurityUser(m)).get();
	       
	    }


}
