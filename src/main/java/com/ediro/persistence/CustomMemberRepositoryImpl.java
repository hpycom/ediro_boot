package com.ediro.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ediro.domain.Member;
import com.ediro.domain.MemberRole;
import com.ediro.domain.QMember;
import com.ediro.domain.QMemberRole;
import com.ediro.domain.QRoles;
import com.ediro.vo.MemberVO;
import com.querydsl.jpa.JPQLQuery;
@Repository
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
	
	public List<Member> getMemberByMemVO(MemberVO memvo)
	{
		 QMember qmember = QMember.member;
		 QMemberRole memberRoles = QMemberRole.memberRole;
		 QRoles roles = QRoles.roles;
		 
			JPQLQuery<Member> query = from(qmember)
					.innerJoin(qmember.memberRoles,memberRoles)
					.innerJoin(memberRoles.roles,roles)
					.where(roles.roleName.eq("BOOKSTORE"))
					.select(qmember);
			
			if(StringUtils.hasText(memvo.getBossName()))
			{
				   query.where(qmember.bossName.contains(memvo.getBossName()));
			}
			
			if(StringUtils.hasText(memvo.getCompanyName()))
			{
				   query.where(qmember.companyName.contains(memvo.getCompanyName()));
			}
			
		   return query.fetch();
	}
}
