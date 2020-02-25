package com.ediro.service;

import java.util.Optional;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ediro.domain.Member;
import com.ediro.domain.MemberRole;
import com.ediro.domain.Roles;
import com.ediro.persistence.MemberRepository;
import com.ediro.persistence.MemberRoleRepository;
import com.ediro.persistence.RoleRepository;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	MemberRoleRepository memRoleRepo;
	
	@Transactional
	public void saveNewMember(Member member)
	{
		memberRepo.save(member);
		
		Optional<Member> newMember = memberRepo.findByMemberID(member.getMemberID());
		
		Roles role = roleRepo.findRolesByRoleName("BOOKSTORE");
		
	    MemberRole memRole = new MemberRole();
	    memRole.setMember(newMember.get());
	    memRole.setRoles(role);
	    
	    memRoleRepo.save(memRole);
		
	}
}
