package com.ediro.service;

import java.util.List;
import java.util.Optional;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ediro.domain.Member;
import com.ediro.domain.MemberRole;
import com.ediro.domain.Roles;
import com.ediro.persistence.CustomMemberRepository;
import com.ediro.persistence.MemberRepository;
import com.ediro.persistence.MemberRoleRepository;
import com.ediro.persistence.RoleRepository;
import com.ediro.security.SecurityConfig;
import com.ediro.vo.MemberVO;

import lombok.extern.java.Log;
@Log
@Service
 public class MemberService {
	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	MemberRoleRepository memRoleRepo;
	
	@Autowired
	CustomMemberRepository cMemRepo;
	
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
	
	@Transactional
	public List<MemberRole> getMemRols(Member member)
	{
		log.info(String.valueOf(member.getMid()));
		List<MemberRole> lstMemRoles = (List<MemberRole>) memRoleRepo.findByMember(member);
		return	lstMemRoles;
	
	}
	
	public List<Member> getCusMembers(MemberVO memVo)
	{
		List<Member> lstMem =  (List<Member>) cMemRepo.getMemberByMemVO(memVo);
		return lstMem;
	}
	
	
	
	
	
}
