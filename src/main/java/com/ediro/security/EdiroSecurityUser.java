package com.ediro.security;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.ediro.domain.Member;
import com.ediro.domain.MemberRole;
import com.ediro.domain.Roles;
import com.ediro.persistence.MemberRepository;
import com.ediro.service.MemberService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.jdo.annotations.Transactional;

/**
 * @author zacconding
 * @Date 2017-12-24
 * @GitHub : https://github.com/zacscoding
 */
@Getter
public class EdiroSecurityUser extends User {
    private static final String ROLE_PREFIX="ROLE_";
    private Member member;
    
    //@Autowired
    //static MemberService memService;
    


   
    public EdiroSecurityUser(Member member,Collection<? extends GrantedAuthority> lstAuth) {
        //super(member.getUid(), "{noop}" + member.getUpw(), makeGrantedAUthority(member.getRoles()));
    	 
       super(member.getMemberID(),  "{noop}" + member.getMemberPwd(),lstAuth);
    	
       this.member = member;
    }

  /*  private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> MemberRoles) {
        List<GrantedAuthority> list = new ArrayList<>();

        MemberRoles.forEach(memberRole -> {
            list.add(new SimpleGrantedAuthority(ROLE_PREFIX + memberRole.getRoles().getRoleName()));
        });

        return list;
    }
    */
    private static List<GrantedAuthority> makeGrantedAuthority(Member member) {
        List<GrantedAuthority> list = new ArrayList<>();
        MemberService memService = new MemberService();
        List<MemberRole> MemberRoles = memService.getMemRols(member);
       
        MemberRoles.forEach(memberRole -> {
            list.add(new SimpleGrantedAuthority(ROLE_PREFIX + memberRole.getRoles().getRoleName()));
        });

        return list;
    }
    
    
}
