package com.vanilla.repository;

import org.springframework.data.repository.CrudRepository;

import com.vanilla.security.Role;



public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByname(String name);
}
