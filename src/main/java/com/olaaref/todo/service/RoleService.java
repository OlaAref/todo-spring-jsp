package com.olaaref.todo.service;

import java.util.List;

import com.olaaref.todo.entity.Role;


public interface RoleService {

	public Role getRole(long id);
	public List<Role> getAllRoles();
	public void saveRole(Role role);
	public void deleteRole(long id);
}
