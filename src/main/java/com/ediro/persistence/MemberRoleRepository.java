package com.ediro.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ediro.domain.Member;
import com.ediro.domain.MemberRole;
import com.ediro.domain.Roles;

@Repository
public interface MemberRoleRepository extends CrudRepository<MemberRole, String> {
   List<MemberRole> findByMember(Member mem);
}
