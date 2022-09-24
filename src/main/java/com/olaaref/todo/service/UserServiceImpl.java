package com.olaaref.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olaaref.todo.dao.RoleDAO;
import com.olaaref.todo.dao.UserDAO;
import com.olaaref.todo.entity.Role;
import com.olaaref.todo.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private RoleDAO roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User getById(long id) {
		return userDao.getById(id);
	}

	@Override
	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public void saveUser(User user) {
		if(user.getId() == 0) {
			Role role = roleDao.getRole(user.getRole().getId());
			user.setRole(role);
			encodePassword(user);
			userDao.saveUser(user);
		}
		else {
			User dbUser = getById(user.getId());
			user.setPassword(dbUser.getPassword());
			userDao.updateUser(user);
		}
	}

	private void encodePassword(User user) {
		String plainPassword = user.getPassword();
		String encodedPassword = passwordEncoder.encode(plainPassword);
		user.setPassword(encodedPassword);
		
	}

	@Override
	public void deleteUser(long id) {
		User user = getById(id);
		userDao.deleteUser(user);
	}

}
