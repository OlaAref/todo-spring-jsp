package com.olaaref.todo.dao;

import java.util.List;

import com.olaaref.todo.entity.Role;

public interface RoleDAO {

	public Role getRole(long id);
	public List<Role> getAllRoles();
	public void saveRole(Role role);
	public void deleteRole(Role role);
}
