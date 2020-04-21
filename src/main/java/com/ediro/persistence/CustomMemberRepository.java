package com.ediro.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ediro.domain.Member;
import com.ediro.domain.MemberRole;
import com.ediro.vo.MemberVO;


@Repository
public interface CustomMemberRepository {
	public List<MemberRole> getMemberRoles(String memberid);
	public List<Member> getMemberByMemVO(MemberVO memvo);
}
