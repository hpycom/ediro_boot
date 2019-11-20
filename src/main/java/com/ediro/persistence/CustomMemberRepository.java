package com.ediro.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ediro.domain.MemberRole;


@Repository
public interface CustomMemberRepository {
	public List<MemberRole> getMemberRoles(String memberid);
}
