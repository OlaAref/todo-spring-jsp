package com.olaaref.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olaaref.todo.dao.RoleDAO;
import com.olaaref.todo.entity.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDao;

	@Override
	public Role getRole(long id) {
		return roleDao.getRole(id);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleDao.getAllRoles();
	}

	@Override
	public void saveRole(Role role) {
		String roleName = "ROLE_" + role.getName().toUpperCase();
		role.setName(roleName);
		roleDao.saveRole(role);
	}


	@Override
	public void deleteRole(long id) {
		Role role = getRole(id);
		roleDao.deleteRole(role);
	}
	
	

}
