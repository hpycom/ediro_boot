package com.ediro.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ediro.domain.MemberRole;
import com.ediro.domain.Roles;

@Repository
public interface MemberRoleRepository extends CrudRepository<MemberRole, String> {

}
