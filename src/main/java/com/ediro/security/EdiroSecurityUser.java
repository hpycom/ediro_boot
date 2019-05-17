package com.ediro.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.ediro.domain.Member;
import com.ediro.domain.MemberRole;
import com.ediro.domain.Roles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author zacconding
 * @Date 2017-12-24
 * @GitHub : https://github.com/zacscoding
 */
@Getter
public class EdiroSecurityUser extends User {
    private static final String ROLE_PREFIX="ROLE_";
    private Member member;

    
    public EdiroSecurityUser(Member member) {
        //super(member.getUid(), "{noop}" + member.getUpw(), makeGrantedAUthority(member.getRoles()));
    	 
       super(member.getMemberID(),  "{noop}" + member.getMemberPwd(), makeGrantedAuthority(member.getMemberRoles()));
       this.member = member;
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> MemberRoles) {
        List<GrantedAuthority> list = new ArrayList<>();

        MemberRoles.forEach(memberRole -> {
            list.add(new SimpleGrantedAuthority(ROLE_PREFIX + memberRole.getRoles().getRoleName()));
        });

        return list;
    }
}
