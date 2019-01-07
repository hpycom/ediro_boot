package com.ediro.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.ediro.domain.Member;
import com.ediro.domain.MemberRole;

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
        super(member.getMemberID(),  "{noop}" + member.getMemberPwd(), makeGrantedAUthority(member.getRoles()));
        this.member = member;
    }

    private static List<GrantedAuthority> makeGrantedAUthority(List<MemberRole> roles) {
        List<GrantedAuthority> list = new ArrayList<>();

        roles.forEach(role -> {
            list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName()));
        });

        return list;
    }
}
