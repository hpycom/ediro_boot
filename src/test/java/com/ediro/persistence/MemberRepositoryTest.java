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
import com.ediro.domain.Roles;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
 
    @PersistenceContext
    private EntityManager em;
    
    @Test
    @Transactional
    public void insert() {
   
    	
          /* Member member = new Member();
            member.setMemberID("bookStore2");
            member.setMemberPwd("pw02");
            member.setCompanyName("테스트서점2");
            em.persist(member);*/
           Roles role = em.find(Roles.class, 1);
    	
           Member member = em.find(Member.class, "bookStore");
           /* List<>
           member.setMemberRoles();
        */
       //     em.persist(member);
            
           
           
            MemberRole memRole = new MemberRole();
            memRole.setMember(member);
            memRole.setRoles(role);
            
        	member.getMemberRoles().add(memRole);
        	
        	//role.getMembers().add(memRole);
        //    em.persist(memRole);
       //     member.setRoles(roles);(Arrays.asList(role));
            em.persist(memRole);
         
            em.flush();
            
       // } 
        	//	);*/
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