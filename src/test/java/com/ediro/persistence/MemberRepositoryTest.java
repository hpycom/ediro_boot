package com.ediro.persistence;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.ediro.domain.Member;
import com.ediro.domain.MemberRole;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author zacconding
 * @Date 2017-12-24
 * @GitHub : https://github.com/zacscoding
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void insert() {
        IntStream.range(0,2).forEach(
        		
        		i -> {
            Member member = new Member();
            member.setMemberID("user" + i);
            member.setMemberPwd("pw"+i);
            member.setCompanyName("사용자"+i);

            MemberRole role = new MemberRole();
            if(i <= 0) {
                role.setRoleName("BOOKSTORE");
            }
            else if(i<=1) {
                role.setRoleName("PUBLISER");
            }
            else {
                role.setRoleName("ADMIN");
            }
            member.setRoles(Arrays.asList(role));
            memberRepository.save(member);
        }
        
        		);
    }

    //@Transactional
    @Test
    public void read() {
        String uid = "user85";
        Optional<Member> result = memberRepository.findById(uid);
        result.ifPresent(member -> {
            log.info("## member : " + member);
        });
    }

}