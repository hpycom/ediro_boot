package com.ediro.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.ediro.domain.Roles;

@Repository
public interface RoleRepository extends CrudRepository<Roles, String> {

	public Roles findRolesByRoleName(String roleName);
}
