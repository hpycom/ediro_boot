package com.ediro.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


import com.ediro.domain.Member;
import com.ediro.domain.MemberRole;
import com.ediro.domain.QMember;
import com.ediro.domain.QMemberRole;

import com.querydsl.jpa.JPQLQuery;

public class CustomMemberRepositoryImpl  extends QuerydslRepositorySupport implements CustomMemberRepository {
	public CustomMemberRepositoryImpl() {
		super(Member.class);
	}
	
	public List<MemberRole> getMemberRoles(String memberid)
	{
		  QMemberRole memberRoles = QMemberRole.memberRole;
		  QMember qmember = QMember.member;
		  
			
			JPQLQuery<MemberRole> query = from(memberRoles)
					.innerJoin(memberRoles.member,qmember)
		    		.where(memberRoles.member.memberID.toUpperCase().eq(memberid.toUpperCase()))
		    		.select(memberRoles);
		    		
		   return query.fetch();
	}
}
